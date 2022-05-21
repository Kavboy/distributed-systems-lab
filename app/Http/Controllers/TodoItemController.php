<?php

namespace App\Http\Controllers;

use App\Models\TodoItem;
use Illuminate\Database\QueryException;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Log;

class TodoItemController extends Controller
{
    /**
     * Display all todos.
     *
     * @OA\Get(
     *     path="/todos",
     *     tags={"todos"},
     *     summary="Get all todos",
     *     description="Returns all todos",
     *     operationId="index",
     *     @OA\Response(
     *         response=200,
     *         description="OK",
     *         @OA\JsonContent(
     *              type="array",
     *              @OA\Items(ref="#/components/schemas/TodoItem")
     *         )
     *     ),
     *     @OA\Response(
     *         response=404,
     *         description="Todo not found"
     *     ),
     *     @OA\Response(
     *         response=500,
     *         description="Something unexpected happened, please try again later"
     *     )
     * )
     *
     * @return \Illuminate\Http\JsonResponse
     */
    public function index() {
        try {
            if ( TodoItem::all() ) {
                $roles = TodoItem::all();

                return response()->json( $roles, 200 );
            } else {
                return response()->json( [], 404 );
            }
        } catch ( QueryException $ex ) {
            if ( env( 'APP_DEBUG' ) ) {
                $res['message'] = $ex->getMessage();
            } else {
                Log::error( $ex );
                $res['message'] = 'Something unexpected happened, please try again later';
            }

            return response()->json( $res, 500 );
        }
    }

     /**
     * Get the specified todo by name.
     *
     * @OA\Get(
     *     path="/todos/{name}",
     *     tags={"todos"},
     *     summary="Get todo by name",
     *     description="Returns a single todo",
     *     operationId="getTodoByName",
     *     @OA\Parameter(
     *         name="name",
     *         in="path",
     *         description="Todo name",
     *         required=true,
     *         @OA\Schema(
     *             type="string"
     *         )
     *     ),
     *     @OA\Response(
     *         response=200,
     *         description="OK",
     *         @OA\JsonContent(ref="#/components/schemas/TodoItem")
     *     ),
     *     @OA\Response(
     *         response=404,
     *         description="Todo not found"
     *     ),
     *     @OA\Response(
     *         response=500,
     *         description="Something unexpected happened, please try again later"
     *     )
     * )
     *
     * @param string name
     *
     * @return \Illuminate\Http\JsonResponse
     */
    public function getTodoByName( string $name ) {
        try {
            if ( TodoItem::find( $name ) ) {
                $todoItem = TodoItem::find( $name );

                return response()->json( $todoItem, 200 );
            } else {
                return response()->json( [
                    'message' => 'Todo not found',
                ], 404 );
            }
        } catch ( QueryException $ex ) {

            if ( env( 'APP_DEBUG' ) ) {
                $res['message'] = $ex->getMessage();
            } else {
                Log::error( $ex );
                $res['message'] = 'Something unexpected happened, please try again later';
            }

            return response()->json( $res, 500 );
        }
    }

    /**
     * Store a newly created todo in storage.
     *
     * @OA\Post(
     *     path="/todos",
     *     tags={"todos"},
     *     operationId="store",
     *      @OA\Response(
     *         response=201,
     *         description="Created successfully",
     *         @OA\JsonContent(ref="#/components/schemas/TodoItem")
     *     ),
     *     @OA\Response(
     *         response=500,
     *         description="Something unexpected happened, please try again later"
     *     ),
     *     @OA\RequestBody(
     *         request="TodoItemArray",
     *         description="List of todo objects",
     *         required=true,
     *         @OA\JsonContent(ref="#/components/schemas/TodoItem")
     *     )
     * )
     *
     * @param \Illuminate\Http\Request $request
     *
     * @return \Illuminate\Http\Response
     */
    public function store( Request $request ) {
        $validated = $request->validate( [
            'todo' => [ 'required', 'string', 'unique:TodoItems' ],
            'priority' => ['required', 'numeric']
        ] );

        try {
            $todoItem = TodoItem::create( [
                'todo' => $validated['todo'],
                'priority' => $validated['priority'],
            ] );

            return response( $todoItem, 201 );
        } catch ( QueryException $ex ) {
            if ( env( 'APP_DEBUG' ) ) {
                $res['message'] = $ex->getMessage();
            } else {
                Log::error( $ex );
                $res['message'] = 'Something unexpected happened, please try again later';
            }

            return response( $res, 500 );
        }
    }

    /**
     * Update the specified todo in storage.
     *
     * @OA\Put(
     *     path="/todos",
     *     tags={"todos"},
     *     operationId="updateTodoItem",
     *     @OA\Response(
     *         response=200,
     *         description="OK",
     *         @OA\JsonContent(ref="#/components/schemas/TodoItem")
     *     ),
     *     @OA\Response(
     *         response=500,
     *         description="Something unexpected happened, please try again later"
     *     ),
     *     @OA\RequestBody(
     *         request="TodoItemArray",
     *         description="List of todo objects",
     *         required=true,
     *         @OA\JsonContent(ref="#/components/schemas/TodoItem")
     *     )
     * )
     *
     * @param \Illuminate\Http\Request $request
     * @param string name
     *
     * @return \Illuminate\Http\Response
     */
    public function update( Request $request ) {
        $validated = $request->validate( [
            'todo' => ['required', 'string'],
            'priority' => [ 'required', 'numeric'],
        ] );

        try {
            $todoItem = TodoItem::find( $validated['todo'] );

            $todoItem->priority = $validated['priority'];

            $todoItem->save();

            return response( $todoItem, 200 );
        } catch ( QueryException $ex ) {
            if ( env( 'APP_DEBUG' ) ) {
                $res['message'] = $ex->getMessage();
            } else {
                Log::error( $ex );
                $res['message'] = 'Something unexpected happened, please try again later';
            }

            return response( $res, 500 );
        }
    }

     /**
     * Remove the specified todo from storage.
     *
     * @OA\Delete(
     *     path="/todos/{name}",
     *     tags={"todos"},
     *     summary="Deletes a pet",
     *     operationId="deletePet",
     *     @OA\Parameter(
     *         name="name",
     *         in="path",
     *         description="Todo name",
     *         required=true,
     *         @OA\Schema(
     *             type="string"
     *         ),
     *     ),
     *     @OA\Response(
     *         response=200,
     *         description="Successfully deleted",
     *     ),
     *     @OA\Response(
     *         response=404,
     *         description="Todo not found",
     *     ),
     *     @OA\Response(
     *         response=500,
     *         description="Something unexpected happened, please try again later"
     *     )
     * )
     *
     * @param string name
     *
     * @return \Illuminate\Http\JsonResponse
     */
    public function destroy( string $name ) {
        try {
            if ( TodoItem::find( $name ) ) {
                TodoItem::find( $name )->delete();

                return response()->json( [
                    'message' => 'Successfully deleted'
                ], 200 );
            }

            return response()->json( [
                'message' => 'Todo not found',
            ], 404 );

        } catch ( QueryException $ex ) {
            if ( env( 'APP_DEBUG' ) ) {
                $res['message'] = $ex->getMessage();
            } else {
                Log::error( $ex );
                $res['message'] = 'Something unexpected happened, please try again later';
            }

            return response()->json( $res, 500 );
        }
    }
}
