package com.pixelo.health.wellplate.core.filter;

/**security 연결때 */
public class AuthenticationFilter {
//    private UserService userService;
//    private Environment env; // 토큰 키.. 가져오기
//    private JwtProvider jwtProvider;
//    private ObjectMapper objectMapper;
//
//    private final static String HEADER_AUTHORIZATION = "Authorization";
//    private final static String TOKEN_PREFIX = "Bearer ";
//
//    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, Environment env, JwtProvider jwtProvider, ObjectMapper objectMapper) {
//        super.setAuthenticationManager(authenticationManager);
//        this.userService = userService;
//        this.env = env;
//        this.jwtProvider = jwtProvider;
//        this.objectMapper = objectMapper;
//    }
//
//    // login 시도시 첫 실행 지점
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            var creds = objectMapper.readValue(request.getInputStream(), RequestLogin.class);
//
//            //(이메일, 비밀번호, 권한) => 토큰 생성
//            var authenticationToken = new UsernamePasswordAuthenticationToken(creds.email(), creds.password());
//            return getAuthenticationManager().authenticate(authenticationToken);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /** 실제 로그인 성공했을떄 어떤 처리를 해줄 것인지*/
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException {
//        //loadUserByUsername 반환된 user 객체 - email값이 있다
//        String email = ((User) (authResult.getPrincipal())).getUsername();
//
//        super.successfulAuthentication(request, response, chain, authResult);
//    }
}
