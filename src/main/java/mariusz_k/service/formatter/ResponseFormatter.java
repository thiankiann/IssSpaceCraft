package mariusz_k.service.formatter;

import java.net.http.HttpResponse;

public interface ResponseFormatter {
    String formatResponse(HttpResponse<String> response);
}
