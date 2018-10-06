/**
 * Express configuration
 */

import express from 'express';
import expressStaticGzip from 'express-static-gzip';
import favicon from 'serve-favicon';
import morgan from 'morgan';
import compression from 'compression';
import bodyParser from 'body-parser';
import methodOverride from 'method-override';
import cookieParser from 'cookie-parser';
import errorHandler from 'errorhandler';
import path from 'path';

import config from './environment';

export default function(app) {
    var env = process.env.NODE_ENV;

    if(env === 'development' || env === 'test') {
        app.use(express.static(path.join(config.root, '.tmp')));
        app.use(require('cors')());
    }

    if(env === 'production') {
        app.use(favicon(path.join(config.root, 'client', 'favicon.ico')));
    }

    app.set('appPath', path.join(config.root, 'client'));
    app.use(express.static(app.get('appPath')));
    if(env === 'production') {
        app.use('/', expressStaticGzip(app.get('appPath')));
    }
    app.use(morgan('dev'));

    app.set('views', `${config.root}/server/views`);
    app.engine('html', require('ejs').renderFile);
    app.set('view engine', 'html');
    app.use(compression());
    app.use(bodyParser.urlencoded({ extended: false }));
    app.use(bodyParser.json());
    app.use(methodOverride());
    app.use(cookieParser());


    if(env === 'development' || env === 'test') {
        app.use(errorHandler()); // Error handler - has to be last
    }
}
