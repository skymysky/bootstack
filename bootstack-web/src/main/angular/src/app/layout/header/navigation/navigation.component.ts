import {animate, Component, OnInit, state, style, transition, trigger} from '@angular/core';
import {SharedService} from "../../../shared/services/shared.service";
import {UserService} from "../../../../services/user.service";
import {Router} from "@angular/router";
import {CookieUtils} from "../../../shared/utils/cookie.util";
import {CodeConfig} from "../../../../config/code.config";
import {ToastyService} from "ng2-toasty";

@Component({
    selector: 'bootstack-navigation',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.scss'],
    animations: [
        trigger('toggleHeight', [
            state('inactive', style({
                height: '0',
                opacity: '0'
            })),
            state('active', style({
                height: '*',
                opacity: '1'
            })),
            transition('inactive => active', animate('200ms ease-in')),
            transition('active => inactive', animate('200ms ease-out'))
        ])
    ]
})

export class NavigationComponent implements OnInit {

    user;

    sidebarVisible: boolean;

    // Sub menu visibilities
    navigationSubState: any = {
        Tables: 'inactive',
        Forms: 'inactive',
        SamplePages: 'inactive',
        UserInterface: 'inactive',
        Components: 'inactive',
        Charts: 'inactive',
    };

    // The secondary menu
    toggleNavigationSub(menu, event) {
        event.preventDefault();
        this.navigationSubState[menu] = (this.navigationSubState[menu] === 'inactive' ? 'active' : 'inactive');
    }

    constructor(private router: Router,
                private sharedService: SharedService,
                private toastyService: ToastyService,
                private userService: UserService) {
        sharedService.sidebarVisibilitySubject.subscribe((value) => {
            this.sidebarVisible = value;
        })
    }

    ngOnInit() {
        this.initUserInfo();
    }

    logout() {
        this.userService.logout();
        this.router.navigate(['/user/login']);
    }

    initUserInfo() {
        this.userService.getInfo(CookieUtils.getUserName()).subscribe(
            response => {
                if (response.code !== CodeConfig.SUCCESS) {
                    this.toastyService.error(response.message);
                }
            }
        );
    }

}
