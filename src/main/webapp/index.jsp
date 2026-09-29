<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<main class="fundo" role="main">
        <img src="imagens/img01.jpg" class="img-fluid" alt="Imagem de fundo">
        <div class="container-inicio">
            <h1>Seja muito Bem-vindo!</h1>
            <div class="place">
                <h2>Hotel Scalabrin</h2>
                <p>Experimente o melhor da hospitalidade em Fraiburgo, Santa
                    Catarina.</p>
                <img src="imagens/fraiburgo.jpg" width="156px" height="156px" alt="Logo do Hotel calabrin">
                <p>
                    Fraiburgo, carinhosamente chamada de “Terra da Maçã”, é um encantador município 
					situado no meio-oeste de Santa Catarina. Fundada em 1961, a cidade se destaca 
					por sua rica herança cultural e beleza natural exuberante. Os fraiburenses são 
					conhecidos pela sua hospitalidade e acolhimento caloroso.
                </p>
                <p>
                    Se está de visita na nossa cidade e procura um lugar para descansar, com excelente
                    atendimento e um ambiente familiar, não procure mais! Venha nos visitar e descubra
                    o melhor serviço. Seja para uma estadia curta ou longa, o Hotel Scalabrin oferece
                    uma experiência única e memorável para todos que passam por Fraiburgo.
                </p>
            </div>
        </div>
    </main>
    <section class="servicos">
        <h2>O que oferecemos?</h2>
        <div class="cards">
            <div class="card">
                <img src="imagens/cafe.png" alt="Ícone Café da Manhã">
                <h3>Café da manhã</h3>
                <p>
                    Oferecemos uma variedade de opções saborosas para agradar a todos os paladares. 
                    Desfrute de pães, bolos, frutas frescas, cereais, iogurtes e suco. Nosso café da 
                    manhã é preparado com ingredientes de alta qualidade para garantir uma experiência 
                    gastronômica única.
                </p>
            </div>
            <div class="card">
                <img src="imagens/banho-quente.png" alt="Ícone Banho quente">
                <h3>Banho quente</h3>
                <p>
                    Relaxe e revitalize-se com nossos chuveiros de água quente, disponíveis 24 horas
                    por dia. No Hotel Scalabrin, garantimos que você tenha uma experiência de banho
                    confortável e agradável, com água na temperatura ideal para seu bem-estar a
                    qualquer hora.
                </p>
            </div>
            <div class="card">
                <img src="imagens/wifi.png" alt="Air Conditioning Icon">
                <h3>Wifi livre</h3>
                <p>
                    Nossa rede Wi-Fi está disponível em todas as áreas do hotel, garantindo que você 
                    possa acessar a internet com facilidade e segurança. Trabalhe ou relaxe assistindo 
                    seus conteúdos favoritos sem limites e sem preocupações.
                </p>
            </div>
            <div class="card">
                <img src="imagens/atendimento.png" alt="Air Conditioning Icon">
                <h3>Bom atendimento</h3>
                <p>
                    Nosso compromisso é proporcionar um atendimento de excelência, sempre com um
                    sorrisso no rosto e prontos para atender as suas necessidades. Seja para
                    informações turísticas ou qualquer outra solicitação, estamos aqui para garantir
                    que sua estadia seja agradável. 
                </p>
            </div>
        </div>
    </section>
    <aside class="complements">
        <h2>Conheça nossas instalações</h2>
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
            <!-- Início indicadores para navegar nos slides do carousel -->
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                    aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                    aria-label="Slide 3"></button>
            </div>
            <!-- Fim indicadores para navegar nos slides do carousel -->
    
            <!-- Início slide carousel -->
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="imagens/exemplo1.jpg" height="300" class="d-block w-100" alt="Categoria 1">
                </div>
                <div class="carousel-item">
                    <img src="imagens/exemplo2.jpg" height="300" class="d-block w-100" alt="Categoria 2">
                </div>
                <div class="carousel-item">
                    <img src="imagens/exemplo3.jpg" height="300" class="d-block w-100" alt="Categoria 3">
                </div>
            </div>
            <!-- Fim slide carousel -->
    
            <!-- Início anterior e próximo slide carousel -->
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
            <!-- Fim anterior e próximo slide carousel -->
        </div>
    </aside>
    <section class="avaliacao">
        <div class="container-my-5">
            <h2 class="text-center">O QUE DIZEM NOSSOS CLIENTES?</h2>
            <div id="carouselExample" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="feedback-box">
                            <p class="star-rating">★★★★★</p>
                            <p>Comida excelente, momentos de descontração e um amplo espaço de convivência.</p>
                            <p class="user-name">ALAN SANTOS</p>
                            <p class="user-role">Avaliação Google</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="feedback-box">
                            <p class="star-rating">★★★★★</p>
                            <p>Ambiente agradável, atendimento impecável e quartos confortáveis. Recomendo!</p>
                            <p class="user-name">GABRIELLA SILVA</p>
                            <p class="user-role">Avaliação TripAdvisor</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="feedback-box">
                            <p class="star-rating">★★★★★</p>
                            <p>Excelente localização, próximo aos principais pontos turísticos. Ótimo custo-benefício.</p>
                            <p class="user-name">JOÃO PEREIRA</p>
                            <p class="user-role">Avaliação Booking.com</p>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                    <span class="visually-hidden">Anterior</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                    <span class="visually-hidden">Próximo</span>
                </button>
            </div>
        </div>
    </section>
    <section class="chamada-acao">
        <div class="container-acao">
            <h2>Gostaria de se hospedar conosco?</h2>
            <p>Então faça sua reserva clicando no botão abaixo e entre em contato conosco pelo WhatsApp.</p>
            <a href="https://wa.me/554932462800?text=Olá,%20tudo%20bem%20com%20você?%0AGostaria%20de%20fazer%20uma%20reserva?%0A😀%20😀%20😀%20😀"
                class="cta-button" target="_blank">
                Fazer Reserva
            </a>
        </div>
    </section>