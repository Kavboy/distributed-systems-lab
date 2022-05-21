<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 * Class TodoItem.
 *
 *
 * @OA\Schema(
 *     description="TodoItem model",
 *     title="TodoItem model",
 *     required={"todo", "priority"},
 *     @OA\Xml(
 *         name="TodoItem"
 *     )
 * )
 *
 */
class TodoItem extends Model
{
    use HasFactory;

    protected  $primaryKey = 'todo';

    /**
     * @OA\Property(
     *     format="string",
     *     description="todo",
     *     title="todo",
     * )
     *
     * @var string
     */
    private $todo;

     /**
     * @OA\Property(
     *     format="int64",
     *     description="priority",
     *     title="priority",
     * )
     *
     * @var int
     */
    private $priority;

    public $table = 'TodoItems';
    public $timestamps = false;
    public $incrementing = false;
    public $keyType = 'string';

     /**
     * The attributes that are hidden in the response.
     *
     * @var array
     */
    protected $hidden = ['id'];

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'todo',
        'priority'
    ];
}
