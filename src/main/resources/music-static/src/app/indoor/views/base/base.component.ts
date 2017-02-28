import {Component, OnInit} from "@angular/core";
import {PlayerService} from "../../../services/player.service";
import {UploadService} from "../../../services/upload.service";
import {SongService} from "../../../services/song.service";
import {Router} from "@angular/router";
@Component({
  selector: 'm-base',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.css'], providers: [PlayerService, UploadService, SongService]
})
export class BaseComponent implements OnInit {
  search: string = "";

  constructor(private _router: Router) {
  }

  ngOnInit() {
    this._router.events.subscribe(it => {
      let values: string[] = it.url.split("/");
      console.log(values);
      if (values.length > 0) {
        if (values[1] == 'filter') {
          if (values.length>2&&values[2].trim()!="") {
            this.search = values[2];
            console.log("set to be "+this.search)
          } else this.search = ""
        } else this.search = ""
      } else this.search = ""
    });
  }
}
