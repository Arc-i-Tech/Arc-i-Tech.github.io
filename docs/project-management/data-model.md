### Data Model
```mermaid
flowchart LR;
  f1(Feature)
  e1([Epic-1])
  e2([Epic-2])
  e3([..])
  us1{{User-Story-1}}
  us2{{User-Story-2}}
  us3{{...}}
  us4{{User-Story-3}}
  us5{{...}}
  t1[Task-1]
  t2[Task-2]
  t3[Task-3]
  t4[Task-4]
  t5[Task-5]
  b1[Bug-1]
  b2[Bug-2]
  b3[Bug-3]
  b4[Bug-4]
  
  f1 --> e1
  f1 --> e2
  f1 --> e3
  e1 --> us1
  e1 --> us2
  e1 --> us3
  e1 --> t5
  e2 --> us4
  e2 --> us5
  e2 --> b4
  us1 --> t1
  us1 --> b1
  us1 --> t2
  us1 --> b2
  us1 --> ...
  us2 --> t3
  us2 --> t4
  us4 --> b3
  us2 --> ....
  us4 --> .....
```