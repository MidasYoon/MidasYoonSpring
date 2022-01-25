const mix = require('laravel-mix');
const webpack = require('webpack');

/*
 |--------------------------------------------------------------------------
 | Mix Asset Management
 |--------------------------------------------------------------------------
 |
 | Mix provides a clean, fluent API for defining some Webpack build steps
 | for your Laravel applications. By default, we are compiling the CSS
 | file for the application as well as bundling up all the JS files.
 |
 */

mix
    .js([
        'src/main/resources/assets/jsx/app.jsx',
        'src/main/resources/assets/jsx/pages/index/indexPage.jsx'
    ], 'js/index.js')
    .setPublicPath("src/main/resources/static/");
    // .js('resources/assets/jsx/pages/index/indexPage.jsx', 'public/jsx/index.js')
    // .js('resources/assets/jsx/pages/member/memberPage.jsx', 'public/jsx/member.js')
    // .js('resources/assets/jsx/pages/member/memberInPage.jsx', 'public/jsx/member_in.js')
    // .js('resources/assets/jsx/pages/board/boardPage.jsx', 'public/jsx/board.js')
    // .js('resources/assets/jsx/pages/board/boardAdminPage.jsx', 'public/jsx/board_ad.js')
    // .js('resources/assets/jsx/pages/prog/progPage.jsx', 'public/jsx/prog.js')
    // .js('resources/assets/jsx/pages/prog/progAdminPage.jsx', 'public/jsx/prog_ad.js')
    // .js('resources/assets/jsx/pages/transport/transportPage.jsx', 'public/jsx/transport.js')
    // .js('resources/assets/jsx/pages/transport/transportAdminPage.jsx', 'public/jsx/transport_ad.js')
    // .js('resources/assets/jsx/pages/travel/travelPage.jsx', 'public/jsx/travel.js')
    // .js('resources/assets/jsx/pages/travel/travelInPage.jsx', 'public/jsx/travel_in.js')
    // .js('resources/assets/jsx/pages/laboratory/laboratoryPage.jsx', 'public/jsx/laboratory.js')
    // .js('resources/assets/jsx/pages/error/errorPage.jsx', 'public/jsx/error.js')
    // .postCss('resources/assets/css/app.css', 'public/css', [
    //     //
    // ]);

mix.webpackConfig({
    entry: {
        vendor: ["@material-ui/styles"],
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin({
            name: "style",
            filename: 'js/[name].js',
            minChunks: Infinity,
        }),
    ]
});