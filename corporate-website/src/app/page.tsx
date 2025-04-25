import Image from "next/image";
import Link from 'next/link';

export default function Home() {
  return (
    <div className="min-h-screen">
      {/* 英雄区域 */}
      <div className="relative bg-gray-900 text-white">
        <div className="absolute inset-0">
          <Image
            src="/xp.jpg"
            alt="背景图片"
            fill
            sizes="100vw"
            style={{
              objectFit: 'cover',
              opacity: 0.5
            }}
            priority
          />
        </div>
        <div className="relative max-w-7xl mx-auto py-24 px-4 sm:py-32 sm:px-6 lg:px-8">
          <h1 className="text-4xl font-extrabold tracking-tight sm:text-5xl lg:text-6xl">
            专业的企业服务提供商
          </h1>
          <p className="mt-6 text-xl max-w-3xl">
            我们致力于为客户提供最优质的服务和解决方案，帮助企业实现数字化转型和业务增长。
          </p>
          <div className="mt-10">
            <Link
              href="/contact"
              className="inline-block bg-indigo-600 py-3 px-8 border border-transparent rounded-md text-base font-medium text-white hover:bg-indigo-700"
            >
              联系我们
            </Link>
          </div>
        </div>
      </div>

      {/* 服务介绍 */}
      <div className="py-12 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <h2 className="text-3xl font-extrabold text-gray-900 sm:text-4xl">
              我们的服务
            </h2>
            <p className="mt-4 text-lg text-gray-500">
              我们提供全方位的企业服务，满足您的各种需求
            </p>
          </div>

          <div className="mt-10">
            <div className="grid grid-cols-1 gap-10 sm:grid-cols-2 lg:grid-cols-3">
              {/* 服务卡片 1 */}
              <div className="bg-white overflow-hidden shadow rounded-lg">
                <div className="px-4 py-5 sm:p-6">
                  <h3 className="text-lg font-medium text-gray-900">企业咨询</h3>
                  <p className="mt-2 text-base text-gray-500">
                    提供专业的商业咨询和战略规划服务，帮助企业实现可持续发展。
                  </p>
                </div>
              </div>

              {/* 服务卡片 2 */}
              <div className="bg-white overflow-hidden shadow rounded-lg">
                <div className="px-4 py-5 sm:p-6">
                  <h3 className="text-lg font-medium text-gray-900">技术解决方案</h3>
                  <p className="mt-2 text-base text-gray-500">
                    提供定制化的技术解决方案，助力企业数字化转型。
                  </p>
                </div>
              </div>

              {/* 服务卡片 3 */}
              <div className="bg-white overflow-hidden shadow rounded-lg">
                <div className="px-4 py-5 sm:p-6">
                  <h3 className="text-lg font-medium text-gray-900">培训服务</h3>
                  <p className="mt-2 text-base text-gray-500">
                    提供专业的员工培训和发展计划，提升企业核心竞争力。
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* 客户评价 */}
      <div className="bg-gray-50 py-12">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <h2 className="text-3xl font-extrabold text-gray-900 sm:text-4xl">
              客户评价
            </h2>
          </div>

          <div className="mt-10">
            <div className="grid grid-cols-1 gap-8 md:grid-cols-2">
              <div className="bg-white p-6 rounded-lg shadow">
                <p className="text-gray-600">
                  "这家公司的服务非常专业，帮助我们解决了长期困扰的问题。"
                </p>
                <div className="mt-4">
                  <p className="font-medium text-gray-900">张先生</p>
                  <p className="text-sm text-gray-500">某科技公司 CEO</p>
                </div>
              </div>

              <div className="bg-white p-6 rounded-lg shadow">
                <p className="text-gray-600">
                  "他们的解决方案非常实用，大大提高了我们的工作效率。"
                </p>
                <div className="mt-4">
                  <p className="font-medium text-gray-900">李女士</p>
                  <p className="text-sm text-gray-500">某金融公司 运营总监</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
