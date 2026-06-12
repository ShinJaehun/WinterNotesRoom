# WinterNotesRoom

WinterNotesRoom은 Winter Notes 계열에서 Room DB 기반 로컬 저장 구조를 중심으로 다시 정리한 Android 노트앱입니다.

Firebase 같은 클라우드 저장 요소를 걷어내고, 로컬 DB에 노트를 저장하는 구조를 명확히 보기 위해 만든 버전입니다.

---

## 이 저장소에 대하여

이 저장소는 Winter Notes 계열의 Room 중심 버전입니다.

Winter Notes 계열은 같은 노트앱 아이디어를 바탕으로 여러 저장 방식과 Android 구조를 실험한 프로젝트군입니다.

- `WinterNotes`: Room 기반 초기 버전
- `WinterNotesV2`: Room + Firebase 혼합 실험 버전
- `WinterNotesRoom`: Room 중심 로컬 저장 버전
- `WinterNotesFirebase`: Firebase 중심 클라우드 저장 버전

WinterNotesRoom은 그중 로컬 저장 구조를 가장 단순하고 명확하게 보기 위해 분리한 버전입니다.

---

## 주요 특징

### 1. Room 기반 로컬 노트 저장

노트 데이터는 Room DB에 저장합니다.

Room 저장 모델은 `RoomNote`로 구성되어 있으며, `winter_notes` 테이블을 사용합니다.

저장되는 주요 정보는 다음과 같습니다.

- 노트 ID
- 제목
- 본문
- 작성 시간
- 이미지 경로
- 색상
- 웹 링크

### 2. DAO 기반 데이터 접근

`NoteDao`를 통해 노트 데이터를 조회하고 저장합니다.

지원하는 주요 기능은 다음과 같습니다.

- 전체 노트 조회
- 노트 ID 기반 조회
- 노트 삭제
- 노트 추가 및 수정

전체 노트는 작성 시간 기준 내림차순으로 조회하도록 구성했습니다.

### 3. 도메인 모델과 저장 모델 분리 시도

앱에서 사용하는 노트 모델과 Room에 저장하는 모델을 나누어 관리하려고 했습니다.

- `Note`: 앱에서 사용하는 노트 데이터 모델
- `RoomNote`: Room DB에 저장되는 Entity 모델

이 구조는 이후 Repository 계층이나 Mapper를 도입할 때 확장하기 좋은 방향입니다.

### 4. Firebase 없는 단순 구조

WinterNotesV2와 달리 Firebase 의존성을 제거하고 Room 중심으로 정리했습니다.

이를 통해 다음 내용을 더 명확히 볼 수 있습니다.

- 로컬 DB 저장 흐름
- Room Entity / DAO 구성
- 노트 CRUD 구조
- 오프라인 중심 노트앱 구조

### 5. 이미지 로딩 구성

Coil을 사용해 이미지가 포함된 노트 표시를 처리할 수 있도록 구성했습니다.

노트에는 이미지 경로를 저장할 수 있으며, 향후 로컬 파일 저장 방식과 연계할 수 있습니다.

---

## 기술 스택

- Android
- Kotlin
- Gradle Kotlin DSL
- Version Catalog
- KSP
- Room
- Android Navigation
- ViewBinding
- Coil
- Material Components
- ConstraintLayout

---

## 프로젝트 성격

WinterNotesRoom은 Android Room DB와 로컬 저장 구조를 학습하기 위한 프로젝트입니다.

특히 다음 내용을 연습하는 데 초점을 두었습니다.

- Room Entity 구성
- DAO 기반 데이터 접근
- 로컬 DB 기반 CRUD
- 앱 모델과 저장 모델 분리
- Firebase 없는 단순 로컬 저장 구조
- 이미지가 포함된 노트 모델
- Android Navigation과 ViewBinding 구성
- 최신 Android Gradle 설정 정리

---

## Winter Notes 계열에서의 위치

WinterNotesRoom은 WinterNotesV2에서 실험했던 여러 기능 중 Room 로컬 저장 방향만 분리해 정리한 버전입니다.

WinterNotesV2가 Room과 Firebase를 함께 넣은 혼합 실험이었다면, WinterNotesRoom은 로컬 저장 앱의 구조를 명확히 보기 위한 버전입니다.

이 저장소는 포트폴리오에서 “Room DB 기반 Android 노트앱 구조 학습”을 보여주는 역할을 합니다.

---

## 현재 상태

- Room 기반 노트 Entity 구현
- DAO 기반 노트 조회/저장/삭제 구현
- 도메인 노트 모델 구성
- Room 저장 모델 구성
- Android Navigation 구성
- ViewBinding 적용
- Coil 이미지 로딩 의존성 구성
- 로컬 저장 중심 실험 버전으로 보관

---

## 앞으로의 개선 방향

가능한 개선 방향은 다음과 같습니다.

- Repository 계층 도입
- Entity와 Domain Model 사이 Mapper 추가
- ViewModel 기반 UI 상태 관리 강화
- Flow 기반 노트 목록 관찰
- 이미지 파일 저장 정책 정리
- 검색 기능 복원
- 테스트 코드 추가
- Clean Architecture 구조로 재정리

---

## 저작권

- WinterNotesRoom은 신재훈이 만들었습니다.
- WinterNotesRoom은 GNU GPL을 따릅니다.
