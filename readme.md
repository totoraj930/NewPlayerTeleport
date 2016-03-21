# NewPlayerTeleport

初めてJoinしたプレイヤーを設定された座標にテレポートさせるプラグインです。

Spigot-1.8.8で動作テストしています。

## コマンド(Commands)

`/npt <set|reload|tp [Player]>`

nptはnewplayerteleportでもかまいません。

* set
  - 現在の自分の座標をテレポートさせる座標にします。
* reload
  - config.ymlをリロードします。
* tp
  - 設定されている座標にプレイヤーをテレポートさせます。  
プレイヤーを指定しなかった場合、自分がテレポートされます。

## パーミッション(Permissions)

* newplayerteleport.*
  - NewPlayerTeleportの全権限
* newplayerteleport.command.npt
  - nptコマンドの権限


## 著者(Author)
**Reona Oshima (totoraj)**
* [http://totoraj.net](http://totoraj.net/)
* [Twitter: @totoraj930](https://twitter.com/totoraj930/)


## ライセンス(License)
Copyright &copy; 2016 Reona Oshima (totoraj)  
This work is released  under the MIT License.  
<http://opensource.org/licenses/mit-license.php>

