// middleware.js
import { NextResponse } from 'next/server';

export function middleware(request) {
  const isLoggedIn = request.cookies.get('JSESSIONID'); // Spring session cookie

  if (!isLoggedIn && request.nextUrl.pathname.startsWith('/dashboard')) {
    return NextResponse.redirect(new URL('/login', request.url));
  }

  return NextResponse.next();
}
