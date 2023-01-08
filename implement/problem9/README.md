# 문자열 압축

https://school.programmers.co.kr/learn/courses/30/lessons/60057

### 문제 해결

- 자를 수 있는 길이는 1 ~ length/2
- 반복되는 개수 체크해서 string 으로 변환 후 자리수 체크
  - 0도 더해지는 상황 발생 -> 1로 변경
- 마지막이 반복되는 문자면 더해지지 않고 끝나는 문제 발생
  - 마지막 한번 더 더해주기
