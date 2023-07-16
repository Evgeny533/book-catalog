package practice.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDto {
    @JsonView({BookDTO.AllInfo.class, BookDTO.NotAllInfo.class})
    private Integer status;
    @JsonView({BookDTO.AllInfo.class, BookDTO.NotAllInfo.class})
    private String statusCode;
    @JsonView({BookDTO.AllInfo.class, BookDTO.NotAllInfo.class})
    private String statusText;
    @JsonView({BookDTO.AllInfo.class, BookDTO.NotAllInfo.class})
    private Object data;


    public void setResponse(Integer status, String statusCode, String statusText, Object data) {
        this.setStatus(status);
        this.setStatusCode(statusCode);
        this.setStatusText(statusText);
        this.setData(data);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
