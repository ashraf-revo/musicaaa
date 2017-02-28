import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {SongService} from "../../../services/song.service";
import {Song} from "../../../domain/Song";
import {SearchCriteria} from "../../../domain/SearchCriteria";
import {Page} from "../../../domain/Page";

@Component({
  selector: 'm-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  search: string = '';
  songs: Song[] = [];
  current: number = 0;
  size: number = 12;
  load: boolean = true;

  constructor(private _router: Router, private _activatedRoute: ActivatedRoute, private _songService: SongService) {
  }

  ngOnInit() {
    this._activatedRoute.params.subscribe(v => {
      this.search = v['data'];
      this.current = 0;
      this.loadMore()
    });
  }

  loadMore() {
    let criteria = this.build();
    this._songService.songsSearchAndGet(criteria).subscribe(songs => {
      if (criteria.search != this.search){ this.songs = [];
        this.current = 0;
      }
      songs.forEach(s => {
        this.songs.push(s);
      });
      if (songs.length < this.size) {
        this.load = false;
      }
    })
  }

  build(): SearchCriteria {
    let pageRequest: Page = new Page();
    let searchCriteria: SearchCriteria = new SearchCriteria();
    searchCriteria.search = this.search;
    pageRequest.number = this.current;
    pageRequest.size = this.size;
    searchCriteria.page = pageRequest;
    return searchCriteria;
  }
}
