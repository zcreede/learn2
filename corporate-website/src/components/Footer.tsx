import Link from 'next/link';

export default function Footer() {
  return (
    <footer className="bg-gray-800 text-white">
      <div className="max-w-7xl mx-auto py-12 px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          <div>
            <h3 className="text-lg font-semibold mb-4">关于我们</h3>
            <p className="text-gray-300">
              我们是一家专业的企业服务提供商，致力于为客户提供最优质的服务和解决方案。
            </p>
          </div>
          <div>
            <h3 className="text-lg font-semibold mb-4">快速链接</h3>
            <ul className="space-y-2">
              <li>
                <Link href="/" className="text-gray-300 hover:text-white">
                  首页
                </Link>
              </li>
              <li>
                <Link href="/about" className="text-gray-300 hover:text-white">
                  关于我们
                </Link>
              </li>
              <li>
                <Link href="/products" className="text-gray-300 hover:text-white">
                  产品服务
                </Link>
              </li>
              <li>
                <Link href="/contact" className="text-gray-300 hover:text-white">
                  联系我们
                </Link>
              </li>
            </ul>
          </div>
          <div>
            <h3 className="text-lg font-semibold mb-4">联系我们</h3>
            <ul className="space-y-2 text-gray-300">
              <li>地址：北京市朝阳区xxx街道xxx号</li>
              <li>电话：010-12345678</li>
              <li>邮箱：contact@example.com</li>
            </ul>
          </div>
        </div>
        <div className="mt-8 pt-8 border-t border-gray-700 text-center text-gray-300">
          <p>&copy; {new Date().getFullYear()} 企业名称. 保留所有权利.</p>
        </div>
      </div>
    </footer>
  );
} 