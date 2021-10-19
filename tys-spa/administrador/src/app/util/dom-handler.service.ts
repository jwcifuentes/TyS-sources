import { Injectable } from '@angular/core';
import {ElementRef, Renderer2, RendererFactory2} from '@angular/core';

@Injectable()
export class DomHandlerService {

  private renderer: Renderer2;

  constructor(private rendererFactory: RendererFactory2) {
    this.renderer = rendererFactory.createRenderer(null, null);
  }

  public addSpaces( spacer:ElementRef, spaces: number, ): void{

    for( let i=0;i<spaces;i++ ){
      const br = document.createElement('br');
      this.renderer.appendChild(spacer.nativeElement, br);
    }

  }

}
