/*
 * orbital_angular_momentum.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:35 by COMSOL 6.3.0.290. */
public class orbital_angular_momentum {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Beam_Propagation");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ewbe", "ElectromagneticWavesBeamEnvelopes", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewbe", true);

    model.param().set("lda0", "1[um]");
    model.param().descr("lda0", "\u6ce2\u957f");
    model.param().set("w0", "10*lda0");
    model.param().descr("w0", "\u5149\u6591\u534a\u5f84");
    model.param().set("zR", "pi*w0^2/lda0");
    model.param().descr("zR", "\u745e\u5229\u8303\u56f4");
    model.param().set("E0", "1[V/m]");
    model.param().descr("E0", "\u7535\u573a\u5927\u5c0f");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "3*w0");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "3*lda0");
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").camera().set("viewscaletype", "manual");
    model.component("comp1").view("view1").camera().set("xscale", 50);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"1"});

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").label("\u5149\u6591\u534a\u5f84");
    model.component("comp1").func("an1").set("funcname", "w");
    model.component("comp1").func("an1").set("expr", "w0*sqrt(1+(x/zR)^2)");
    model.component("comp1").func("an1").setIndex("argunit", "m", 0);
    model.component("comp1").func("an1").set("fununit", "m");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u5149\u675f\u53d8\u91cf");
    model.component("comp1").variable("var1").set("rho", "sqrt(y^2+z^2)");
    model.component("comp1").variable("var1").descr("rho", "\u6a2a\u5411\u5f84\u5411\u5750\u6807");
    model.component("comp1").variable("var1").set("phi", "atan2(y,z)");
    model.component("comp1").variable("var1").descr("phi", "\u6a2a\u5411\u65b9\u4f4d\u89d2\u5750\u6807");
    model.component("comp1").variable("var1").set("EG0", "E0*rho*sqrt(2)/w(x)*exp(j*phi)*exp(j*atan(x/zR))");
    model.component("comp1").variable("var1").descr("EG0", "\u9ad8\u65af\u5149\u675f\u632f\u5e45");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u7ed8\u56fe\u53d8\u91cf");
    model.component("comp1").variable("var2").set("argEy", "arg(ewbe.Ey)");
    model.component("comp1").variable("var2").descr("argEy", "\u7535\u573a y \u5206\u91cf\u7684\u590d\u53d8\u5143");
    model.component("comp1").variable("var2").set("argWindow", "if(argEy<-pi/2,-2*pi,if(argEy>pi/2,2*pi,argEy))");
    model.component("comp1").variable("var2")
         .descr("argWindow", "[-pi/2,pi/2] \u8303\u56f4\u5185\u7684\u590d\u53d8\u5143");

    model.component("comp1").physics("ewbe").prop("WaveVector").set("dirCount", "UniDirectionality");
    model.component("comp1").physics("ewbe").prop("ShapeProperty").set("shapeorder", "2t2");
    model.component("comp1").physics("ewbe").create("mbc1", "MatchedBoundaryCondition", 2);
    model.component("comp1").physics("ewbe").feature("mbc1").selection().set(1);
    model.component("comp1").physics("ewbe").feature("mbc1").set("IncidentField", "GaussianBeam");
    model.component("comp1").physics("ewbe").feature("mbc1").set("w0", "w0");
    model.component("comp1").physics("ewbe").feature("mbc1").set("Eg0", new String[]{"0", "EG0", "0"});
    model.component("comp1").physics("ewbe").create("mbc2", "MatchedBoundaryCondition", 2);
    model.component("comp1").physics("ewbe").feature("mbc2").selection().set(6);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("wave").set("plist", "lda0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (ewbe)");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u7535\u573a");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").create("mslc2", "Multislice");
    model.result("pg1").feature("mslc2").set("expr", "argEy");
    model.result("pg1").feature("mslc2").set("xnumber", "5");
    model.result("pg1").feature("mslc2").set("ynumber", "0");
    model.result("pg1").feature("mslc2").set("znumber", "0");
    model.result("pg1").run();
    model.result("pg1").feature("mslc2").create("filt1", "Filter");
    model.result("pg1").run();
    model.result("pg1").feature("mslc2").feature("filt1").set("expr", "rho<2.5*w0");
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").active(false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u76f8\u56fe");
    model.result("pg2").set("edgecolor", "gray");
    model.result("pg2").create("iso1", "Isosurface");
    model.result("pg2").feature("iso1").set("expr", "argWindow");
    model.result("pg2").feature("iso1").set("number", 1);
    model.result("pg2").feature("iso1").set("resolution", "finer");
    model.result("pg2").feature("iso1").set("useder", false);
    model.result("pg2").feature("iso1").create("filt1", "Filter");
    model.result("pg2").run();
    model.result("pg2").feature("iso1").feature("filt1").set("expr", "rho<2.5*w0&&abs(arg(ewbe.Ey))<pi/2");
    model.result("pg2").run();
    model.result("pg2").feature("iso1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("iso1").feature("col1").set("expr", "ewbe.Ey");
    model.result("pg2").feature("iso1").feature("col1").set("colortable", "HeatCameraLight");
    model.result("pg2").run();

    model.view().create("view2", 3);
    model.view("view2").camera().set("viewscaletype", "automatic");

    model.result("pg2").run();
    model.result("pg2").set("view", "view2");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "ewbe.Ey");
    model.result("pg2").feature("surf1").set("titletype", "none");
    model.result("pg2").feature("surf1").set("colortable", "Cividis");
    model.result("pg2").feature("surf1").set("colorlegend", false);
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().set(2, 4, 5);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("sel1").selection().set(6);
    model.result("pg2").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").set("sweeptype", "dde");
    model.result().export("anim1").set("repeat", "forever");

    model.title("\u8f68\u9053\u89d2\u52a8\u91cf\u5149\u675f");

    model
         .description("\u672c\u6a21\u578b\u5229\u7528\u5355\u5411\u6ce2\u516c\u5f0f\uff0c\u901a\u8fc7\u201c\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\u201d\u63a5\u53e3\u6a21\u62df\u62c9\u76d6\u5c14\u9ad8\u65af\u5149\u675f\uff0c\u8fd9\u79cd\u805a\u7126\u9ad8\u65af\u8f93\u5165\u5149\u675f\u5177\u6709\u87ba\u65cb\u76f8\u4f4d\u5206\u5e03\u3002\n\n\u8fd9\u79cd\u76f8\u4f4d\u5206\u5e03\u4ea7\u751f\u9ad8\u65af\u73af\u5f62\u5149\u675f\uff0c\u5728\u4f20\u64ad\u8fc7\u7a0b\u4e2d\uff0c\u76f8\u4f4d\u7ed5\u5149\u8f74\u65cb\u8f6c\u3002\u4ea7\u751f\u7684\u5149\u675f\u79f0\u4e3a\u6da1\u65cb\u5149\u675f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("orbital_angular_momentum.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
