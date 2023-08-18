package amadeus.caseStudy.core.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET = "7d7338023d68c35577fbfafb86152b4116a01ad8ca3bb8e07cfba904b3ae8a9b";
	
	public <T> T extractClaim(String jwtToken, Function<Claims,T> resolveClaims){
		final Claims claims = extractAllClaims(jwtToken);
		return resolveClaims.apply(claims);
	}
	
	public String extractUsername(String jwtToken) {
		return extractClaim(jwtToken,Claims::getSubject);
	}
	private Claims extractAllClaims(String jwtToken) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwtToken).getBody();
	}
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails) {
		return Jwts
				.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+500*60*24))
				.signWith(getSignInKey(),SignatureAlgorithm.HS256)
				.compact()
				;
	}
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(),userDetails);
	}
	
	public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
		final String username = extractUsername(jwtToken);
		return (username.equals(userDetails.getUsername())&& !isTokenExpired(jwtToken)
				);
	}
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	private Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}

}
