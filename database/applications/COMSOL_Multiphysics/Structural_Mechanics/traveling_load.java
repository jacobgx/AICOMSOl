/*
 * traveling_load.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:38 by COMSOL 6.3.0.290. */
public class traveling_load {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Structural_Mechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("SpanWidth", "10[m]", "\u5355\u8de8\u5bbd\u5ea6");
    model.param().set("NumSpans", "8", "\u8de8\u6570");
    model.param().set("TotLength", "SpanWidth*NumSpans", "\u6881\u603b\u957f");
    model.param().set("BeamHeight", "0.3[m]", "\u6881\u9ad8");
    model.param().set("LoadIntensity", "0.1[MPa]", "\u538b\u529b\u8f7d\u8377\u5f3a\u5ea6");
    model.param().set("PulseWidth", "2[m]", "\u8f7d\u8377\u8109\u51b2\u5bbd\u5ea6");
    model.param().set("PulseSpacing", "20[m]", "\u8f7d\u8377\u8109\u51b2\u95f4\u8ddd");
    model.param().set("LoadSpeed", "20[m/s]", "\u8f7d\u8377\u8109\u51b2\u901f\u5ea6");
    model.param().set("Tstep1", "1/(50*f0)", "\u57fa\u4e8e\u56fa\u6709\u9891\u7387\u7684\u65f6\u6b65\u9650\u5236");
    model.param()
         .set("Tstep2", "PulseWidth/LoadSpeed/2", "\u57fa\u4e8e\u8f7d\u8377\u901f\u5ea6\u7684\u65f6\u6b65\u9650\u5236");
    model.param().set("Tstep", "min(Tstep1,Tstep2)", "\u6700\u5927\u65f6\u6b65");
    model.param()
         .set("Tend", "TotLength/LoadSpeed", "\u7b2c\u4e00\u4e2a\u8f7d\u8377\u8109\u51b2\u5230\u8fbe\u6881\u7aef\u7684\u65f6\u95f4");
    model.param().set("ElemPerSpan", "max(ceil(SpanWidth/PulseWidth*2),20)", "\u5355\u8de8\u5355\u5143\u6570");
    model.param()
         .set("f0", "sqrt(25e9[Pa]*BeamHeight^2/(12*2300[kg/m^3]*SpanWidth^4))*pi/2", "\u4e00\u9636\u56fa\u6709\u9891\u7387\u4f30\u8ba1\u503c");
    model.param().set("CriticalSpeed", "f0*2*SpanWidth", "\u4e34\u754c\u901f\u5ea6\u4f30\u8ba1\u503c");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"SpanWidth", "BeamHeight"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "BeamHeight/2", 1);
    model.component("comp1").geom("geom1").feature().duplicate("pt2", "pt1");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "SpanWidth", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("pt1", "pt2", "r1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"NumSpans", "1"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"SpanWidth", "0"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("box1", "Box");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("box1").label("\u6846\u9009\uff1a\u4e2d\u5fc3\u7ebf\u4e0a\u7684\u70b9");
    model.component("comp1").selection("box1").set("entitydim", 0);
    model.component("comp1").selection("box1").set("ymin", "BeamHeight/3");
    model.component("comp1").selection("box1").set("ymax", "2*BeamHeight/3");
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection().create("box2", "Box");
    model.component("comp1").selection("box2").label("\u6846\u9009\uff1a\u4e0a\u65b9\u7684\u8fb9\u754c");
    model.component("comp1").selection("box2").set("entitydim", 1);
    model.component("comp1").selection("box2").set("ymin", "2*BeamHeight/2");
    model.component("comp1").selection("box2").set("condition", "inside");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Concrete");
    model.component("comp1").material("mat1").set("family", "concrete");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"10e-6[1/K]", "0", "0", "0", "10e-6[1/K]", "0", "0", "0", "10e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2300[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.8[W/(m*K)]", "0", "0", "0", "1.8[W/(m*K)]", "0", "0", "0", "1.8[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "880[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "25[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.20");

    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("beta_dK", 0.002);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 0);
    model.component("comp1").physics("solid").feature("fix1").selection().named("box1");

    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("lower", "-0.5*PulseWidth");
    model.component("comp1").func("rect1").set("upper", "0.5*PulseWidth");
    model.component("comp1").func("rect1").set("smooth", "0.1*PulseWidth");
    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "Pulse");
    model.component("comp1").func("an1").set("expr", "rect1(x)");
    model.component("comp1").func("an1").setIndex("argunit", "m", 0);
    model.component("comp1").func("an1").set("fununit", "1");
    model.component("comp1").func("an1").set("periodic", true);
    model.component("comp1").func("an1").set("periodicupper", "PulseSpacing");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("FirstLoadX", "LoadSpeed*t+PulseWidth/2");
    model.component("comp1").variable("var1")
         .descr("FirstLoadX", "\u7b2c\u4e00\u4e2a\u8f7d\u8377\u8109\u51b2\u7684\u4f4d\u7f6e");
    model.component("comp1").variable("var1").set("BehindFirstLoad", "X<FirstLoadX");
    model.component("comp1").variable("var1")
         .descr("BehindFirstLoad", "\u5982\u679c\u5728\u7b2c\u4e00\u4e2a\u8f7d\u8377\u8109\u51b2\u7684\u5f53\u524d\u4f4d\u7f6e\u4e4b\u540e\uff0c\u5219\u4e3a True");

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("box2");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "if(BehindFirstLoad,-LoadIntensity*Pulse(X-LoadSpeed*t),0)", "0"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().named("box2");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", "ElemPerSpan");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "SpanWidth", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "SpanWidth", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "NumSpans", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "", 1);
    model.study("std1").feature("param").setIndex("pname", "NumSpans", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "", 1);
    model.study("std1").feature("param").setIndex("pname", "LoadSpeed", 0);
    model.study("std1").feature("param")
         .setIndex("plistarr", "20[m/s] CriticalSpeed CriticalSpeed CriticalSpeed", 0);
    model.study("std1").feature("param").setIndex("pname", "PulseSpacing", 1);
    model.study("std1").feature("param").setIndex("plistarr", "TotLength*2 TotLength*2 SpanWidth*2 SpanWidth", 1);
    model.study("std1").feature("time").set("tlist", "range(0,SpanWidth/LoadSpeed/20,Tend)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "0.0001");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("maxstepconstraintgenalpha", "const");
    model.sol("sol1").feature("t1").set("maxstepgenalpha", "Tstep");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 161, 0);
    model.result("pg1").setIndex("looplevel", 4, 1);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "v");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", 50);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 161, 0);
    model.result("pg2").setIndex("looplevel", 4, 1);
    model.result("pg2").label("\u8fb9\u754c\u8f7d\u8377 (solid)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg2").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").feature("surf1").set("inheritcolor", false);
    model.result("pg2").feature("surf1").set("inheritrange", false);
    model.result("pg2").feature("surf1").set("inheritheightscale", false);
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", 0);
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg2").feature("surf1").feature("sel1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg2").create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"solid.bndl1.fax", "solid.bndl1.fay"});
    model.result("pg2").feature("arwl1").set("placement", "gausspoints");
    model.result("pg2").feature("arwl1").set("arrowbase", "tail");
    model.result("pg2").feature("arwl1").label("\u8fb9\u754c\u8f7d\u8377 1");
    model.result("pg2").feature("arwl1").set("inheritplot", "none");
    model.result("pg2").feature("arwl1").create("col", "Color");
    model.result("pg2").feature("arwl1").feature("col").set("colortable", "Rainbow");
    model.result("pg2").feature("arwl1").feature("col").set("colortabletrans", "none");
    model.result("pg2").feature("arwl1").feature("col").set("colorscalemode", "linear");
    model.result("pg2").feature("arwl1").feature("col").set("colordata", "arrowlength");
    model.result("pg2").feature("arwl1").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arwl1").feature("col").set("topcolor", "red");
    model.result("pg2").feature("arwl1").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arwl1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg2").feature("arwl1").set("color", "red");
    model.result("pg2").feature("arwl1").create("def", "Deform");
    model.result("pg2").feature("arwl1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg2").feature("arwl1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("arwl1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arwl1").feature("def").set("scale", 0);
    model.result("pg2").feature().move("surf1", 1);
    model.result("pg2").label("\u8fb9\u754c\u8f7d\u8377 (solid)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("arwl1").label("\u8f7d\u8377\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("placement", "uniform");
    model.result("pg2").feature("arwl1").set("arrowcount", 1000);
    model.result("pg2").feature("arwl1").set("arrowbase", "head");
    model.result("pg2").feature("arwl1").set("scaleactive", true);
    model.result("pg2").feature("arwl1").set("scale", "1.5e-5");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("arwl1").feature().remove("col");
    model.result("pg2").feature("arwl1").feature().remove("def");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").feature().copy("arwl1", "pg2/arwl1");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").label("\u652f\u67b6");
    model.result("pg1").feature("arws1").set("expr", new String[]{"0", "1"});
    model.result("pg1").feature("arws1").set("arrowxmethod", "coord");
    model.result("pg1").feature("arws1").set("xcoord", "range(0,SpanWidth,TotLength)");
    model.result("pg1").feature("arws1").set("arrowymethod", "coord");
    model.result("pg1").feature("arws1").set("ycoord", 0);
    model.result("pg1").feature("arws1").set("arrowtype", "cone");
    model.result("pg1").feature("arws1").set("arrowbase", "head");
    model.result("pg1").feature("arws1").set("scaleactive", true);
    model.result("pg1").feature("arws1").set("scale", 2);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{96, 4});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{96, 3});
    model.result("pg1").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg1");
    model.result().export("anim1").run();
    model.result().export("anim1").setIndex("singlelooplevel", 2, 1);
    model.result().export("anim1").run();
    model.result().export("anim1").setIndex("singlelooplevel", 3, 1);
    model.result().export("anim1").run();
    model.result().export("anim1").setIndex("singlelooplevel", 4, 1);
    model.result().export("anim1").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", "SpanWidth/2");
    model.result().dataset("cpt1").set("pointy", "BeamHeight/2");
    model.result().dataset("cpt1").set("data", "dset2");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").set("data", "cpt1");
    model.result("pg2").feature("ptgr1").set("expr", "v");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "t*LoadSpeed");
    model.result("pg2").feature("ptgr1").set("linewidth", 2);
    model.result("pg2").feature("ptgr1").set("linemarker", "cycle");
    model.result("pg2").feature("ptgr1").set("markerpos", "interp");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u7b2c\u4e00\u4e2a\u8f7d\u8377\u8109\u51b2\u7684\u4f4d\u7f6e [m]");
    model.result("pg2").run();

    model.title("\u627f\u53d7\u52a8\u8f7d\u8377\u7684\u6881");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u968f\u7a7a\u95f4\u548c\u65f6\u95f4\u53d8\u5316\u7684\u8f7d\u8377\u3002\u4e00\u7cfb\u5217\u8f7d\u8377\u8109\u51b2\u6cbf\u7740\u53d7\u7b49\u8ddd\u79bb\u652f\u6491\u7684\u6881\u8fdb\u884c\u8fd0\u52a8\u3002\n\n\u8f7d\u8377\u8109\u51b2\u4f20\u64ad\u901f\u5ea6\u4e0e\u8109\u51b2\u95f4\u8ddd\u5728\u67d0\u4e9b\u7ec4\u5408\u4e0b\uff0c\u53ef\u4ee5\u5728\u6881\u4e2d\u6fc0\u53d1\u5171\u632f\u3002\u672c\u4f8b\u7814\u7a76\u56db\u79cd\u4e0d\u540c\u53c2\u6570\u7ec4\u5408\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("traveling_load.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
