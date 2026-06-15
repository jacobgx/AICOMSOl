/*
 * luneburg_lens.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:17 by COMSOL 6.3.0.290. */
public class luneburg_lens {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Lenses_Cameras_and_Telescopes");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.param().set("L", "1[m]");
    model.param().descr("L", "\u7bb1\u957f\u5ea6");
    model.param().set("R", "0.4[m]");
    model.param().descr("R", "\u5916\u534a\u5f84");

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "R");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("r", "sqrt(x^2+y^2+z^2+eps)");
    model.component("comp1").variable("var1").descr("r", "\u5f84\u5411\u5750\u6807");
    model.component("comp1").variable("var1").set("f", "1.0");
    model.component("comp1").variable("var1").descr("f", "\u7126\u79fb\u53c2\u6570");
    model.component("comp1").variable("var1").set("n", "sqrt(1+f^2-(r/R)^2)/f");
    model.component("comp1").variable("var1").descr("n", "\u6298\u5c04\u7387");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n"});

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").feature("matd1").set("ReleaseReflectedRays", "Never");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("GridType", "Hexapolar");
    model.component("comp1").physics("gop").feature("relg1").set("qcc", new double[]{-0.75, 0, 0});
    model.component("comp1").physics("gop").feature("relg1").set("Rc", 0.36);
    model.component("comp1").physics("gop").feature("relg1").setIndex("Ncr", 10, 0);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new int[]{1, 0, 0});

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("llist", "range(0,0.05,1.75)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("maxstepconstraintgenalpha", "const");
    model.sol("sol1").feature("t1").set("maxstepgenalpha", "2.5e-12");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 36, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").label("\u5c04\u7ebf\u56fe 1");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "at(4.5975768e-9,gop.rrel)");
    model.result("pg1").feature("rtrj1").feature("col1").set("unit", "\u00b5m");
    model.result("pg1").feature("rtrj1").feature("col1").set("colortable", "Plasma");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").feature("surf1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("tran1").set("uniformblending", 0.8);
    model.result("pg1").run();
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", "n");
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("colortable", "GrayScale");
    model.result("pg1").feature("mslc1").set("colorlegend", false);
    model.result("pg1").feature("mslc1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").feature("tran1").set("transparency", 0.2);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u70b9\u5217\u56fe");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").set("legendactive", true);
    model.result("pg2").set("legendprecision", 8);
    model.result("pg2").create("spot1", "SpotDiagram");
    model.result("pg2").feature("spot1").set("normal", "userdefined");
    model.result("pg2").feature("spot1").set("normalexpr", new int[]{1, 0, 0});
    model.result().dataset().create("ip1", "IntersectionPoint3D");
    model.result("pg2").feature("spot1").set("data", "ip1");
    model.result().dataset("ip1").set("data", "ray1");
    model.result().dataset("ip1").set("genmethod", "pointnormal");
    model.result().dataset("ip1").setIndex("genpnpoint", "0.400747761406485[m]", 0);
    model.result().dataset("ip1").setIndex("genpnpoint", "-2.5423093158198256E-14[m]", 1);
    model.result().dataset("ip1").setIndex("genpnpoint", "2.3529316240857244E-17[m]", 2);
    model.result().dataset("ip1").set("genpnvec", new double[]{1, 0, 0});
    model.result("pg2").feature("spot1").run();
    model.result().dataset("ip1").set("genmethod", "pointnormal");
    model.result().dataset("ip1").setIndex("genpnpoint", "0.400000355941606[m]", 0);
    model.result().dataset("ip1").setIndex("genpnpoint", "-1.7351443167475652E-14[m]", 1);
    model.result().dataset("ip1").setIndex("genpnpoint", "-5.188644390777608E-17[m]", 2);
    model.result().dataset("ip1").set("genpnvec", new double[]{1, 0, 0});
    model.result("pg2").feature("spot1").run();
    model.result("pg2").set("ispendingzoom", true);
    model.result("pg2").feature("spot1").set("spotcoordsactive", true);
    model.result("pg2").feature("spot1").set("spotcoordssystem", "global");
    model.result("pg2").feature("spot1").set("spotcoordsprecision", 6);
    model.result("pg2").feature("spot1").set("spotsizeprecision", 4);
    model.result("pg2").feature("spot1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("spot1").feature("col1").set("expr", "t");
    model.result("pg2").feature("spot1").feature("col1").set("colortable", "Plasma");
    model.result("pg2").run();

    model.title("\u9f99\u52c3\u900f\u955c");

    model
         .description("\u9f99\u52c3\u900f\u955c\u5177\u6709\u6e10\u53d8\u6298\u5c04\u7387\uff0c\u8fd9\u4f7f\u5176\u5f62\u6210\u4e86\u7279\u6b8a\u7684\u805a\u7126\u7279\u6027\u3002\u672c\u4f8b\u6a21\u578b\u4f7f\u7528\u201c\u51e0\u4f55\u5149\u5b66\u201d\u63a5\u53e3\u6765\u8ba1\u7b97\u6e10\u53d8\u6298\u5c04\u7387\u4ecb\u8d28\u4e2d\u7684\u5f2f\u66f2\u5c04\u7ebf\u8f68\u8ff9\u3002");

    model.label("luneburg_lens.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
