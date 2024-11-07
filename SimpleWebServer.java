import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleWebServer {
    public static void main(String[] args) throws IOException {
        // 8080 포트로 서버 생성
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // "/" 경로에 대한 처리
        server.createContext("/", new MyHandler());
        
        // 서버 시작
        server.start();
        System.out.println("http://localhost:8080/ 접속");
    }
    
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // 웹 페이지 내용
            String response = "<h1>Hello World!</h1>";
            
            // HTTP 헤더 설정
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            
            // 응답 전송
            exchange.sendResponseHeaders(200, response.getBytes().length);
            
            // 응답 본문 작성
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}