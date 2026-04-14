export default function AuthLayout({
  children,
}: {
  children: React.ReactNode;
}) {
    console.log('AuthLayout está siendo usado');
  return <>{children}</>;
}