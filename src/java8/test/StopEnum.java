package java8.test;

/**
 * @author: young
 * @create: 2019/6/20 10:18
 * @desc: 站点相关枚举
 */
public class StopEnum {
    enum StopType {
        START(1),
        VIA(2),
        END(3),
        ;

        private Integer code;

        StopType(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }

    enum OperType {
        REACH(1),
        LEAVE(2),
        ;

        private Integer code;

        OperType(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }

    enum OperFrom {
        MANUAL(1),
        AUTO(2),
        ;

        private Integer code;

        OperFrom(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }
}
