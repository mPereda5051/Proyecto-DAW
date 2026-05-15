import './privacidad.css';

export default function Privacidad() {
  return (
    <main className="privacidad-container">
      <h1>Política de Privacidad</h1>
      <p>
        En Jinbu nos tomamos muy en serio la privacidad de nuestros usuarios.
        Esta política describe cómo tratamos tu información personal.
      </p>
      <h2>Datos que recopilamos</h2>
      <p>
        Recopilamos únicamente los datos necesarios para el funcionamiento de la
        plataforma: nombre de usuario, correo electrónico y las fotografías que
        decides publicar.
      </p>
      <h2>Uso de los datos</h2>
      <p>
        Tus datos se usan exclusivamente para ofrecerte el servicio. No los
        vendemos ni compartimos con terceros.
      </p>
      <h2>Tus derechos</h2>
      <p>
        Puedes solicitar la eliminación de tu cuenta y todos tus datos en
        cualquier momento escribiendo a <span style={{ color: 'var(--primary-color)' }}>privacidad@jinbu.com</span>.
      </p>
    </main>
  );
}
