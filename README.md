1. Fordítási hiba nincs	
2. Futtatási hiba nincs	
3. Firebase autentikáció meg van valósítva: Be lehet jelentkezni és regisztrálni
  - Bejelentkezés csak regisztrált felhasználóként (email+jelszó)
  - Sikeres regisztráció után a bejelentkezett felhasználóként BookingActivity
4. Beviteli mezők beviteli típusa megfelelő (jelszó kicsillagozva, email-nél megfelelő billentyűzet jelenik meg stb.)
  - Login - email (szöveg) és jelszó (csillagozva)
  - Regisztráció - email (szöveg), jelszó és jelszó megerősítése (csillagozva), telefonszám (numerikus billentyűzet)
5. ConstraintLayout és még egy másik layout típus használata
  - LinearLayout- activity_login,
  - ConstraintLayout - activity_booking
6. Reszponzív: - különböző kijelző méreteken is jól jelennek meg a GUI elemek (akár tableten is) - elforgatás esetén is igényes marad a layout
  - ScrollView login és registration layout (land)
7. Egy animáció használata
  - LoginActivity: - login -> button_anim; - registration -> button_anim 
8. Intentek használata: navigáció meg van valósítva az activityk között (minden activity elérhető)
  - Bejelentkezés-> Booking - sikeres bejelentkezés után
  - Bejelentkezés ->Regisztráció - regiszráció gomb után
  - Regisztráció -> Booking - sikeres regisztráció után
  - Regisztráció -> Bejelentkezés - mégsem gomb után
  - Booking -> Bejelentkezés - kijelentkezés után
9. Szubjektív pontozás a projekt egészére vonatkozólag: ez 3-ról indul és le lehet vonni, ha igénytelen, összecsapott, látszik hogy nem foglalkozott vele, kísértetiesen hasonlít a videóban létrehozotthoz stb.
