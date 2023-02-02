# Image gallery

Aplikacja zawarta na tym repozytorium oferuje możliwość tworzenia i zarządzania galeriami zdjęć.

## Backend

Backend został napisany w Javie przy użyciu frameworku Spring. W ramach backendu zostało wystawione REST API z wystawionymi endpointami 
odpowiedzialnymi za tworzenie galerii, dodawanie i usuwanie zdjęć, zmianę nazwy galerii oraz jej usuwanie. Aby skorzystać z tych funkcjonalności 
należy stworzyć konto i zalogować się na nie - również za pomocą odpowiednich endpointów. Wszystkie endpointy oprócz `/register`, `/token` oraz `/login` wymagają 
autoryzacji tokenem JWT, uzyskanym w odpowiedzi na request `POST` skierowanym z login i hasłem na endpoint `/login` lub `/token`. Oba endpointy różnią się tylko tym, że 
`/login`, oprócz tokenu JWT, zwraca również z powrotem username.

Backend można uruchomić poleceniem `gradlew bootRun` z poziomu katalogu głównego projektu. Backend pracuje na porcie `8080`.

## Frontend

Fronted wykorzystuje Vue.js 3, vue-router 4, vuex 4, axios, vee-validate 4, bootstrap oraz vue-fontawesome. W ramach frontendu można skorzystać z większości endpointów backendu. Po rejestracji i logowaniu, na podstronie `/galleries` można tworzyć, przeglądać i zarządzać galeriami.

Frontend uruchamiamy poleceniem `npm run serve` z poziomu podkatalogu `/frontend/src` względem katalogu głównego. Frontend pracuje na porcie `8081`.
