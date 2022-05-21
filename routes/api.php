<?php

use App\Http\Controllers\TodoItemController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::group( [ 'prefix' => '/todos' ], function () {
    Route::get( '', [ TodoItemController::class, 'index' ] );
    Route::get( '/{name}', [ TodoItemController::class, 'getTodoByName' ] );
    Route::post( '', [TodoItemController::class, 'store']);
    Route::delete( '/{name}', [TodoItemController::class, 'destroy']);
    Route::put( '', [TodoItemController::class, 'update']);
});
