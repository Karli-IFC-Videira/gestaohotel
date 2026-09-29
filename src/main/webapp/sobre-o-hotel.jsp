<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 <jsp:include page="header.jsp"/>
 <main>
     <section class="box-historia">
        <div class="text-section">
            <h2>História do Hotel</h2>
            <p>
                Nossa jornada começou há várias décadas com muito esforço e sacrifícios. Ao longo dos anos, construímos
                uma tradição de hospitalidade e excelência. Cada ação nossa reflete a paixão e o cuidado que temos em
                proporcionar uma experiência única para nossos hóspedes. Venha descobrir como é o nosso dia a dia e faça
                parte dessa história incrível. Estamos ansiosos para recebê-lo e criar memórias inesquecíveis juntos.
            </p>
        </div>
        <div class="carousel-container">
            <div id="carouselExampleAutoplayingHist" class="carousel slide" data-bs-ride="carousel"
                data-bs-interval="5000">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="imagens/scalabrin1.jpg" class="d-block w-100" alt="Imagem da fachada do hotel Scalabrin">
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="box-turismo">
        <article class="text-section">
            <h2>Locais para Visitar</h2>
            <p>
                Se você está buscando conhecer os lugares mais emblemáticos de Fraiburgo, não perca a oportunidade de
                explorar
                os pontos turísticos mencionados a seguir.
            </p>
        </article>
        <div class="carousel-container">
            <div id="carouselExampleAutoplayingTur1" class="carousel slide" data-bs-ride="carousel"
                data-bs-interval="5000">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="imagens/Portal_Fraiburgo.jpeg" class="d-block w-100" alt="Marco 1">
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="box-turismo-casa-cultura">
        <article class="text-section">
            <h2>Casa da Cultura Lydia Frey</h2>
            <p>
                A Casa da Cultura Lydia Frey foi construída em 1948 e foi a residência do casal Lydia e Arnoldo Frey, os
                quais também foram fundadores do município.
            </p>
            <p>
                Atualmente, o Museu é um dos mais belos atrativos da Terra da Maçã.
            </p>
        </article>
        <div class="carousel-container">
            <div id="carouselExampleAutoplayingCasaCultura" class="carousel slide" data-bs-ride="carousel"
                data-bs-interval="5000">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="imagens/casa-lidya-frey.jpg" class="d-block w-100" alt="Museu Casa da Cultura Lydia Frey - 1">
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="box-turismo-museu-jagunco">
        <div class="carousel-container">
            <div id="carouselExampleAutoplayingTur2" class="carousel slide" data-bs-ride="carousel"
                data-bs-interval="5000">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="imagens/museu-do-jagunco.jpg" class="d-block w-100" alt="Museu do Jagunço - 1">
                    </div>
                </div>
            </div>
        </div>
        <article class="text-section">
            <h2>Museu do Jagunço</h2>
            <p>
                O Museu do Jagunço é um espaço que preserva a história e as tradições dos jagunços da região. Conheça a
                cultura e as histórias que moldaram a história local.
            </p>
            <p>
                O acervo inclui objetos, fotos e documentos que relatam a vida e as atividades dos jagunços em
                diferentes
                períodos.
            </p>
        </article>
    </section>

    <section class="box-turismo-praca-frey">
        <article class="text-section">
            <h2>Praça Maria Frey</h2>
            <p>
                A Praça Maria Frey é um local de encontro e celebração para os residentes e visitantes. O espaço conta
                com
                áreas verdes, bancos e uma atmosfera tranquila para passeios e relaxamento.
            </p>
        </article>
        <div class="carousel-container">
            <div id="carouselExampleAutoplayingTur3" class="carousel slide" data-bs-ride="carousel"
                data-bs-interval="5000">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="imagens/praca-maria-frey.jpg" class="d-block w-100" alt="Praça Maria Frey - 1">
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="call-to-action">
        <div class="container-acao">
            <h2>Gostou da nossa cidade?</h2>
            <p>
                Então venha e descubra tudo o que Fraiburgo tem a oferecer. Reserve sua estadia hoje mesmo e comece a sua aventura!
            </p>
            <a href="#" class="cta-button">Reserve Agora</a>
        </div>
    </section>
</main>
   <jsp:include page="footer.jsp"/>