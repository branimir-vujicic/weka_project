# Movie recommendation example

## Dataset

Ovaj skup podataka (ml-latest-small) opisuje ocenu sa 5 zvezdica i aktivnost označavanja slobodnim tekstom
iz [MovieLens](http://movielens.org), usluge za preporuku filmova. Sadrži 100836 ocena i 3683 aplikacije oznaka (tag) u
9742
filma. Ove podatke je kreiralo 610 korisnika između 29. marta 1996. i 24. septembra 2018. Ovaj skup podataka je
generisan 26. septembra 2018.

Kreiran je novi set podataka koji je kombinovan set od datoteka ratings.csv i movies.csv

dataset ima 3 artirbuta : userId, rating i title
primer strukture je:

    userId,rating,title
    1,4,"Toy Story (1995)"
    1,4,"Grumpier Old Men (1995)"
    1,4,"Heat (1995)"
    1,5,"Seven (a.k.a. Se7en) (1995)"
    1,5,"Usual Suspects, The (1995)"
    1,3,"From Dusk Till Dawn (1996)"

##     

            NearestNeighbourSearch collaborativeFilter = new LinearNNSearch();
            collaborativeFilter.setDistanceFunction(new EuclideanDistance());
            collaborativeFilter.setInstances(trainingData);

Algoritam za kolaborativno filtriranje korišćen u datom primeru zasniva se na pristupu k-najbližih suseda.

NearestNeighbourSearch collaborativeFilter = nev LinearNNSearch();: Ova linija kreira instancu klase LinearNNSearch,
koja je implementacija NearestNeighbourSearch interfejsa u Veka. Predstavlja algoritam za pretragu najbližeg suseda koji
se koristi u kolaborativnom filtriranju.

collaborativeFilter.setDistanceFunction(nev EuclideanDistance());: Ovde je funkcija udaljenosti za pretragu najbližeg
suseda podešena na EuclideanDistance. Klasa EuclideanDistance izračunava Euklidsko rastojanje između dve instance na
osnovu vrednosti njihovih atributa. To je uobičajena metrika udaljenosti koja se koristi u kolaborativnom filtriranju.

collaborativeFilter.setInstances(trainingData);: Ova linija postavlja instance ili podatke na kojima će se izvršiti
pretraga najbližeg suseda. U ovom slučaju, podaci o obuci, predstavljeni promenljivom trainingData, daju se kao ulaz.

Algoritam za kolaborativno filtriranje, koji koristi k-najbliže susede, funkcioniše na sledeći način:

- Učitavanje podataka o obuci: Algoritam počinje učitavanjem podataka o obuci, koji sadrže istorijske ocene korisnika za
  filmove.
- Prethodna obrada podataka: Mogu se izvršiti svi neophodni koraci prethodne obrade podataka, kao što je rukovanje
  nedostajućim vrednostima ili normalizacija podataka, kako bi se osigurala tačnost preporuka.
- Pretraga najbližeg suseda: Algoritam za kolaborativno filtriranje vrši pretragu najbližeg suseda na osnovu ulaznih
  podataka. Cilj mu je da pronađe najsličnije korisnike ili stavke na osnovu njihovih obrazaca ocenjivanja.
- Proračun sličnosti: Funkcija udaljenosti, u ovom slučaju, Euklidska udaljenost, koristi se za izračunavanje sličnosti
  između ciljnog korisnika ili stavke i drugih korisnika ili stavki u podacima o obuci. Metrika udaljenosti pomaže da se
  kvantifikuje sličnost na osnovu vrednosti atributa.
- Izbor k-najbližih suseda: Algoritam bira k najsličnijih korisnika ili stavki kao najbližih suseda. Vrednost k određuje
  broj suseda koje treba uzeti u obzir. Od ovih suseda se očekuje da imaju slične obrasce ocenjivanja ciljnog korisnika
  ili artikla.
- Generisanje preporuka: Na osnovu ocena najbližih suseda, algoritam generiše preporuke za ciljnog korisnika ili stavku.
  Može uzeti u obzir ocene najbližih suseda i dati predviđanja ili predloge za filmove koji bi se mogli svideti ciljnom
  korisniku, ali oni još nisu gledali.
  Dati isečak koda postavlja algoritam za kolaborativno filtriranje koristeći pristup k-najbližih suseda. On
  inicijalizuje LinearNNSearch algoritam kao algoritam za pretragu najbližeg suseda i postavlja funkciju udaljenosti na
  Euklidsko rastojanje. Konačno, on postavlja podatke za obuku kao instance na kojima će algoritam izvršiti pretragu
  najbližeg suseda.
  Važno je napomenuti da navedeni fragment koda predstavlja podešavanje i inicijalizaciju algoritma za saradničko
  filtriranje. Kompletan proces generisanja i evaluacije preporuka uključivao bi dodatne korake, kao što je odabir
  ciljnog korisnika, pronalaženje najbližih suseda i generisanje preporuka na osnovu ocena suseda.

## rezultat :

Movie Recommendations:

    5.0 Monty Python's Life of Brian (1979)
    5.0 Star Wars: Episode IV - A New Hope (1977)
    5.0 Tombstone (1993)
    5.0 Sword in the Stone, The (1963)
    5.0 Reservoir Dogs (1992)
    5.0 Raiders of the Lost Ark (Indiana Jones and the Raiders of the Lost Ark) (1981)
    5.0 Indiana Jones and the Last Crusade (1989)
    5.0 Fantasia (1940)
    5.0 M*A*S*H (a.k.a. MASH) (1970)
    5.0 X-Men (2000)

## Evaluacija

Classifier classifier = new NaiveBayes(); Ova linija kreira instancu NaiveBaies klasifikatora iz Weka biblioteke.
NaiveBaies klasifikator je popularan probabilistički klasifikator koji se obično koristi za zadatke klasifikacije.

classifier.buildClassifier(mergedData);: Ova linija gradi klasifikator koristeći podatke obuke (mergedData). Metoda
buildClassifier() obučava klasifikator koristeći date podatke za obuku, omogućavajući mu da nauči obrasce i odnose
između ulaznih atributa i oznaka klasa.

Sistem.out.println(classifier.getCapabilities().toString());: Ova linija ispisuje mogućnosti klasifikatora. Metod
getCapabilities() vraća objekat koji predstavlja mogućnosti klasifikatora, koji uključuje informacije kao što su
podržani tipovi atributa, tipovi klasa i dostupne opcije. Pozivanjem funkcije toString(), mogućnosti se štampaju na
konzoli.

    Capabilities: [Nominal attributes, Binary attributes, Unary attributes, Empty nominal attributes, Numeric attributes, Missing values, Nominal class, Binary class, Missing class values]
    Dependencies: []
    Interfaces: [WeightedAttributesHandler, WeightedInstancesHandler]
    Minimum number of instances: 0

Evaluation evaluation = nev Evaluation(trainingData);: Ova linija kreira instancu Evaluation klase, koja se koristi za
procenu performansi klasifikatora. Klasa Evaluation zahteva da se podaci o obuci (trainingData) navedu kao argument.

evaluation.evaluateModel(classifier, testingData);: Ova linija procenjuje obučeni klasifikator koristeći podatke
testiranja (testingData). Metod evaluateModel() izračunava različite metrike evaluacije da bi procenio performanse
klasifikatora na podacima testiranja. On upoređuje predviđene oznake klasa sa stvarnim oznakama klasa u podacima
testiranja da bi odredio tačnost i druge mere performansi.

Ukratko, ovaj isečak koda obučava naivni Bajesov klasifikator na spojenim podacima obuke (mergedData). Zatim štampa
mogućnosti klasifikatora. Konačno, on procenjuje obučeni klasifikator koristeći podatke testiranja (testingData),
računajući metriku evaluacije da bi procenio njegov učinak.

## Rezultati evaluacije

        Correctly Classified Instances         188                0.9322 %
        Incorrectly Classified Instances     19979               99.0678 %
        Kappa statistic                          0.0074
        Mean absolute error                      0.0002
        Root mean squared error                  0.0102
        Relative absolute error                 99.9061 %
        Root relative squared error            100.5516 %
        Total Number of Instances              20167     

Rezultati evaluacije daju uvid u performanse modela klasifikacije koji se koristi u sistemu preporuka filmova. Hajde da
razgovaramo o različitim metrikama evaluacije i šta nam one govore:

- Tačno klasifikovane instance: Ova metrika ukazuje na broj instanci (preporuka) koje je model pravilno klasifikovao. U
  ovom slučaju, 188 slučajeva od 20167 je tačno klasifikovano, što predstavlja tačnost od 0,9322%. To znači da je model
  tačno predvideo ocene za ove slučajeve.
- Netačno klasifikovane instance: Ova metrika predstavlja broj instanci koje je model pogrešno klasifikovao. U ovom
  slučaju, 19979 slučajeva od 20167 je pogrešno klasifikovano, što odgovara stopi greške od 99,0678%. To znači da se
  predviđanja modela za ove slučajeve nisu poklapala sa stvarnim ocenama.
- Kappa statistika: Kappa statistika meri slaganje između predviđenih i stvarnih ocena, uzimajući u obzir mogućnost
  slaganja samo slučajno. Vrednost blizu 0 ukazuje na lošu saglasnost iznad onoga što se slučajno očekuje, dok vrednost
  blizu 1 ukazuje na snažno slaganje. U ovom slučaju, Kappa statistika je 0,0074, što ukazuje na veoma slabo slaganje
  između predviđenih i stvarnih ocena.
- Srednja apsolutna greška (MAE): MAE predstavlja prosečnu apsolutnu razliku između predviđenih i stvarnih ocena. Niži
  MAE ukazuje na bolju preciznost. U ovom slučaju, MAE je 0,0002, što ukazuje na veoma malu prosečnu razliku između
  predviđenih i stvarnih ocena.
- Srednja srednja kvadratna greška (RMSE): RMSE je kvadratni koren proseka kvadratnih razlika između predviđenih i
  stvarnih ocena. Veće greške kažnjava više od MAE. Niži RMSE ukazuje na bolju preciznost. U ovom slučaju, RMSE je
  0,0102, što ukazuje na veoma malu ukupnu grešku u predviđanjima.
- Relativna apsolutna greška: Relativna apsolutna greška meri MAE u odnosu na opseg stvarnih ocena. Predstavlja prosečnu
  apsolutnu razliku kao procenat ukupnog raspona ocena. U ovom slučaju, relativna apsolutna greška je 99,9061%, što
  ukazuje na veoma malu relativnu grešku u poređenju sa skalom ocenjivanja.
- Korenska relativna kvadratna greška: Korijenska relativna kvadratna greška je RMSE u odnosu na opseg stvarnih ocjena.
  Predstavlja RMSE kao procenat ukupnog raspona ocena. U ovom slučaju, relativna greška na kvadrat iznosi 100,5516%, što
  ukazuje na malu relativnu grešku u poređenju sa skalom ocenjivanja.
- Ukupan broj instanci: Ova metrika jednostavno pokazuje ukupan broj instanci (preporuka) korišćenih u procesu
  evaluacije. U ovom slučaju, ima ukupno 20167 slučajeva.

Na osnovu ovih rezultata evaluacije, možemo videti da je tačnost modela prilično niska, na šta ukazuje nizak procenat
ispravno klasifikovanih instanci. Kappa statistika takođe ukazuje na slabo slaganje između predviđenih i stvarnih ocena.
Međutim, vrednosti MAE i RMSE su veoma male, što ukazuje da su, u proseku, predviđene ocene veoma blizu stvarnim
ocenama. Relativna apsolutna greška i koren relativna greška na kvadrat takođe ukazuju na malu relativnu grešku u
poređenju sa skalom ocenjivanja.

