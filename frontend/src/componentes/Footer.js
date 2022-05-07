import { BsFacebook, BsTwitter, BsInstagram, BsLinkedin } from "react-icons/bs";
export function Footer() {
  return (
    <footer className="site-footer">
      <div className="container">
        <div className="row">
          <div className="col-sm-12 col-md-6">
            <h6>Acerca De</h6>

            <p className="text-justify">
              Encontrá en difficult.com
              <img
                src="https://i.imgur.com/Twx16wi.png"
                alt="loguito"
                className="loguito-footer"
              />
              todo para mejorar el Hogar al mejor precio,
              <br />
              cientos de pisos y pinturas exclusivos. Desde en el año 1956 con
              vos.
            </p>
          </div>
          <div className="col-sm-12 col-md-3">
            <h6>Contactanos</h6>
            <p>011-3110-2021</p>
            <p>difficult.info@yahoo.com.ar</p>
          </div>

          <div className="d-flex flex-column col-md-3 col-sm-12 col-xs-12">
            <h6>Siguenos</h6>
            <div className="social-icons mb-2">
              <li>
                <a href="/login">
                  <BsFacebook />
                </a>
              </li>
              <li>
                <a href="/login">
                  <BsTwitter />
                </a>
              </li>
              <li>
                <a href="/login">
                  <BsInstagram />
                </a>
              </li>
              <li>
                <a href="/login">
                  <BsLinkedin />
                </a>
              </li>
            </div>
          </div>
          <hr />

          <div className="col-md-12 col-sm-12 col-xs-12">
            <p>Copyright &copy; 2022 All Rights Reserved by Difficult.</p>
          </div>
        </div>
      </div>
    </footer>
  );
}
