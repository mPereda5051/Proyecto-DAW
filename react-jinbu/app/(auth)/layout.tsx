/** Layout para las rutas de autenticación (login y registro). No incluye navbar. */
export default function AuthLayout({
  children,
}: {
  children: React.ReactNode;
}) {
    console.log('AuthLayout está siendo usado');
  return <>{children}</>;
}