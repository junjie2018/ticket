package fun.mytoys.ticket.movie.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private Long id;
    private String username;
    private String name;
    private Integer age;
    private BigDecimal balance;

    public UserVo(String username) {
        this.username = username;
    }
}
