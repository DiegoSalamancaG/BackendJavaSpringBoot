package Back_endSpringBoot.infrastructure.security.jwt;

import Back_endSpringBoot.infrastructure.security.service.CustomUserDetail;
import Back_endSpringBoot.infrastructure.security.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro JWT que intercepta cada solicitud HTTP para:
 * - Extraer el token del encabezado Authorization
 * - Validarlo
 * - Autenticar al usuario si el token es válido
 */
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        // Excluir rutas públicas como /api/v1/auth/**
        if (path.startsWith("/api/v1/auth")){
            filterChain.doFilter(request,response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
            try{
                CustomUserDetail userDetail = userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(jwt,userDetail)){
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetail,null,userDetail.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("Token válido. Autenticando a: " + username);
                }
            }catch (Exception e){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token inválido o expirado");
                return;
            }
        }

        filterChain.doFilter(request,response);
        System.out.println("Encabezado Authorization: " + authHeader);
        System.out.println("Token extraído: " + jwt);
        System.out.println("Username extraído: " + username);













    }
}
