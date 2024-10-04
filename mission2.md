```
public boolean validateOrder(Order order){
    if(order.getItems().size() == 0) {
        log.info("주문 항목이 없습니다.");
        return false;
    } else{
        if(order.getTotalPrice() > 0){
            if(!order.hasCustomerInfo()){
                log.info("사용자 정보가 없습니다.");
                return false;
            } else{
                return true;
            }
        }else if(!(order.getTotalPrice() > 0)) {
                log.info("올바르지 않은 총 가격입니다.");
                return false;
        }
    }
    return true;
}
```
### 문제 파악
validateOrder(Order order) - 사용자가 생성한 주문이 유효한지 검증하는 메서드

- 주문서의 아이템들의 개수가 0일 시 log 발생
- 0이 아닐 시, 
    1. 주문서의 가격이 0보다 크면
        a. 주문서의 손님 정보가 존재하지 않을 시 log 발생 
        b. 손님 정보가 존재 할 시 true(주문이 유효하다.) 반환
    2. 주문서의 가격이 0보다 크지 않으면
        a. log 발생

### 1. Early return 체크 + 부정 연산자 제거 + 중첩 줄이기
   - 빨리 리턴 가능하고 else문을 사용하지 않아도 되는 파트를 찾아보기.
     - 주문서의 가격이 0이 아니면 false 반환
     - 총 가격이 0 이하여도 false 반환
     - 손님 정보가 없어도 false 반환
   - 위 세 조건을 제외한 경우에만 true를 반환하면 된다.

```
public boolean validateOrder(Order order){
    if(order.getItems().size() == 0) {
        log.info("주문 항목이 없습니다.");
        return false;
    } 
    if(order.getTotalPrice() <= 0) {
        log.info("올바르지 않은 총 가격입니다.");
        return false;
    }
    if(!order.hasCustomerInfo()){
        log.info("사용자 정보가 없습니다.");
        return false;
    }
    return true;
}
```

### 2. 공백 라인에 의미 가지기
    - 각 if 문마다 판단하는 것이 다르다고 생각해서 각 if문마다 공백을 주는 것으로 변경

```
public boolean validateOrder(Order order){
    if(order.getItems().size() == 0) {
        log.info("주문 항목이 없습니다.");
        return false;
    } 
    
    if(order.getTotalPrice() <= 0) {
        log.info("올바르지 않은 총 가격입니다.");
        return false;
    }
    
    if(!order.hasCustomerInfo()){
        log.info("사용자 정보가 없습니다.");
        return false;
    }
    
    return true;
}
```

### 3. 메서드 추출
   - '!order.hasCustomerInfo()' 역시 불필요한 부정 연산자, 별도의 doesNotHaveCustomerInfo() 메서드를 Order 객체 내부에 생성했다고 가정 후 리팩토링
   - 각 조건문 역시 Order 객체 내부에서 직접 검증하도록 메서드 추출

**본래 메서드가 위치한 클래스**   
```
public boolean validateOrder(Order order){
    if(order.doesNotHaveOrderItem()) {
        log.info("주문 항목이 없습니다.");
        return false;
    } 
    
    if(order.isTotalPriceLessThanZero()) {
        log.info("올바르지 않은 총 가격입니다.");
        return false;
    }
    
    if(order.doesNotHaveCustomerInfo()){
        log.info("사용자 정보가 없습니다.");
        return false;
    }
    
    return true;
}
```

**Order 객체 내부 메서드**
```
...

public boolean doesNotHaveOrderItem(){
    return this.getItems().size() == 0
}

public boolean isTotalPriceLessThanZero(){
    return this.getTotalPrice() <= 0
}

public boolean doesNotHaveCustomerInfo(){
    return this.getCustomerInfo == null;
}

...

```

### 4. 예외 처리
    - OrderValidateException 이라는 예외를 생성 후 예외 처리
    - 아예 예외를 던지고 검증 메서드 자체도 Order 객체 내부에서 처리
    - 본래 메서드는 아예 사라지고, 주문을 할 때 검증이 필요할 경우, order.validateOrder()를 호출하는 방향으로 변화
   
**OrderValidateException**   
```
public class OrderValidateException extends RuntimeException {
    public OrderValidateException(String message) {
        super(message);
    }
}
```

**Order 객체 내부 메서드**
```
...

public void validateOrder(){
    if(doesNotHaveOrderItem()) {
        throw new OrderValidateException("주문 항목이 없습니다.");
    }
    
    if(isTotalPriceLessThanZero()) {
        throw new OrderValidateException("올바르지 않은 총 가격입니다.");
    }
    
    if(doesNotHaveCustomerInfo()){
        throw new OrderValidateException("사용자 정보가 없습니다.");
    }
}

private boolean doesNotHaveOrderItem(){
    return this.getItems().size() == 0
}

private boolean isTotalPriceLessThanZero(){
    return this.getTotalPrice() <= 0
}

private boolean doesNotHaveCustomerInfo(){
    return this.getCustomerInfo == null;
}

...

```