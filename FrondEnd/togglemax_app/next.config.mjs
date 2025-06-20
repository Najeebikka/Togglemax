/** @type {import('next').NextConfig} */
const nextConfig = {
devIndicators: false,
experimental: {
    serverActions: {
      bodySizeLimit: '100mb'  // or '200mb' if needed
    }
  }
};


export default nextConfig;
