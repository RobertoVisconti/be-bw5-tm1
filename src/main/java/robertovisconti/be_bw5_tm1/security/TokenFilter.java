package robertovisconti.be_bw5_tm1.security;

//@Component
//
//public class TokenFilter extends OncePerRequestFilter {
//
//    private HandlerExceptionResolver resolver;
//    private TokenToolkit tokenTools;
//    private UtenteService utenteService;
//
//    public TokenFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver, TokenToolkit tokenTools, UtenteService utenteService) {
//        this.resolver = resolver;
//        this.tokenTools = tokenTools;
//        this.utenteService = utenteService;
//    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        try {
//            String header = request.getHeader("Authorization");
//            if (header == null || !header.startsWith("Bearer "))
//                throw new UnauthorizedException("Il token è mancante o non valido, ricontrolla di aver inserito il token corretto con 'Bearer ' davanti");
//            String token = header.replace("Bearer ", "");
//            this.tokenTools.tokenVerify(token);
//
//            UUID id = this.tokenTools.extractId(token);
//            Utente authUser = this.utenteService.findById(id);
//            Authentication authentication = new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        } catch (Exception ex) {
//            resolver.resolveException(request, response, null, ex);
//        }
//
//
//        filterChain.doFilter(request, response);
//
//    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return new AntPathMatcher().match("/login/**", request.getServletPath());
//    }
//}
