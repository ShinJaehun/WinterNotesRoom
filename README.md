# WinterNotesV2

WinterNotesV2는 WinterNotes의 두 번째 실험 버전입니다. 초기 WinterNotes에서 구현했던 노트앱 구조를 바탕으로, Gradle Kotlin DSL, version catalog, KSP, Room, Firebase를 함께 사용해 보며 Android 앱 구조를 다시 정리한 프로젝트입니다.

---

## 이 저장소에 대하여

이 저장소는 Winter Notes 계열의 중간 실험 버전입니다.

Winter Notes 계열은 같은 노트앱 아이디어를 여러 저장 방식과 구조로 구현해 보며 Android 아키텍처를 학습한 프로젝트군입니다.

- `WinterNotes`: Room 기반 초기 버전
- `WinterNotesV2`: Room + Firebase 혼합 실험 버전
- `WinterNotesRoom`: Room 중심 로컬 저장 버전
- `WinterNotesFirebase`: Firebase 중심 클라우드 저장 버전

WinterNotesV2는 Room과 Firebase를 함께 사용하면서, 로컬 저장과 사용자 기반 클라우드 저장을 어떻게 연결할 수 있을지 실험한 버전입니다.

---

## 주요 특징

### 1. Room + Firebase 혼합 실험

WinterNotesV2는 Room과 Firebase 관련 의존성을 함께 포함합니다.

Room은 로컬 노트 저장을 담당하고, Firebase는 사용자 인증과 클라우드 저장 가능성을 실험하기 위해 사용했습니다.

사용한 Firebase 구성은 다음과 같습니다.

- Firebase Auth
- Firebase Firestore
- Firebase Storage
- Firebase BOM
- Google Services plugin

### 2. 사용자 개념 추가

초기 WinterNotes가 단순한 로컬 노트앱에 가까웠다면, WinterNotesV2는 작성자 개념을 추가했습니다.

노트 모델에는 다음 정보가 포함됩니다.

- 제목
- 본문
- 작성 시간
- 이미지 경로
- 색상
- 웹 링크
- 작성자

사용자 모델은 `uid`와 `email`을 중심으로 구성했습니다.

이를 통해 나중에 여러 사용자가 각자의 노트를 관리하거나, 클라우드 저장소와 연결하는 구조를 실험할 수 있도록 했습니다.

### 3. Room 저장 모델 확장

Room 저장용 모델에는 `creator_id`를 포함했습니다.

이는 로컬 DB 안에서도 특정 사용자가 작성한 노트를 구분할 수 있도록 하기 위한 실험입니다.

### 4. Gradle Kotlin DSL과 version catalog 사용

WinterNotesV2에서는 Gradle 설정을 Kotlin DSL 기반으로 바꾸고, version catalog를 사용해 의존성을 관리했습니다.

이를 통해 Android 프로젝트 설정을 더 명시적으로 관리하고, 라이브러리 버전을 한 곳에서 정리하는 방식을 연습했습니다.

### 5. 이미지 로딩 실험

Coil을 사용해 이미지 로딩을 처리했습니다.

이미지가 포함된 노트앱을 만들기 위해 로컬 이미지 경로와 클라우드 이미지 저장 가능성을 함께 고려했습니다.

---

## 기술 스택

- Android
- Kotlin
- Gradle Kotlin DSL
- Version Catalog
- KSP
- Room
- Firebase Auth
- Firebase Firestore
- Firebase Storage
- Firebase BOM
- Android Navigation
- ViewBinding
- Coroutine
- Coil
- Material Components
- ConstraintLayout

---

## 프로젝트 성격

WinterNotesV2는 하나의 완성된 배포용 앱이라기보다, 여러 Android 기술을 한 앱 안에서 조합해 본 실험 버전입니다.

특히 다음 내용을 학습하는 데 초점을 두었습니다.

- Gradle Kotlin DSL 전환
- version catalog 기반 의존성 관리
- KSP 기반 Room compiler 사용
- Room 로컬 저장 구조
- Firebase 인증과 클라우드 저장 구조
- 사용자 모델과 작성자 개념
- 이미지가 포함된 노트 구조
- 로컬 저장과 클라우드 저장을 함께 고려한 모델 설계

---

## Winter Notes 계열에서의 위치

WinterNotesV2는 초기 WinterNotes 이후의 확장 실험입니다.

이 버전에서는 Room과 Firebase를 함께 넣어 보며 가능성을 확인했습니다. 이후에는 구조를 더 명확히 나누기 위해 다음 두 저장소로 분리했습니다.

- `WinterNotesRoom`: Room 중심 로컬 저장 앱
- `WinterNotesFirebase`: Firebase 중심 클라우드 저장 앱

따라서 WinterNotesV2는 두 방향으로 갈라지기 전의 중간 실험 버전이라고 볼 수 있습니다.

---

## 현재 상태

- Room 의존성 구성
- Firebase Auth / Firestore / Storage 의존성 구성
- 사용자 모델 추가
- 작성자 정보를 포함한 노트 모델 구성
- Gradle Kotlin DSL 적용
- version catalog 적용
- Coil 이미지 로딩 구성
- 학습용 실험 버전으로 보관

---

## 앞으로의 개선 방향

이 저장소 자체를 계속 키우기보다는, Room과 Firebase를 분리한 후속 저장소에서 각각의 구조를 더 명확히 실험했습니다.

가능한 개선 방향은 다음과 같습니다.

- Room 저장소와 Firebase 저장소의 책임 분리
- Repository 인터페이스 도입
- 로컬 우선 / 클라우드 동기화 정책 정리
- 인증 상태에 따른 노트 필터링
- 이미지 업로드와 로컬 캐시 정책 정리
- 테스트 코드 추가

---

## 저작권

- WinterNotesV2는 신재훈이 만들었습니다.
- WinterNotesV2는 GNU GPL을 따릅니다.
