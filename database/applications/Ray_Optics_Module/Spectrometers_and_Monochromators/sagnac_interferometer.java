/*
 * sagnac_interferometer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:21 by COMSOL 6.3.0.290. */
public class sagnac_interferometer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Spectrometers_and_Monochromators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Omega", "10[deg/h]", "\u5e72\u6d89\u4eea\u89d2\u901f\u5ea6");
    model.param().set("lam0", "632.8[nm]", "\u771f\u7a7a\u6ce2\u957f");
    model.param().set("R", "10[cm]", "\u73af\u534a\u5f84");
    model.param().set("th", "3[cm]", "\u9762\u5916\u539a\u5ea6");
    model.param().set("A", "0.5*(1.5*R)*(R*sqrt(3))", "\u4e09\u89d2\u5f62\u9762\u79ef");
    model.param().set("P", "3*(R*sqrt(3))", "\u4e09\u89d2\u5f62\u5468\u957f");
    model.param().set("phi0", "30[deg]", "\u521d\u59cb\u5c04\u7ebf\u89d2\u5ea6");
    model.param().set("xc", "R", "\u65cb\u8f6c\u4e2d\u5fc3\uff0cx \u5750\u6807");
    model.param().set("yc", "0", "\u65cb\u8f6c\u4e2d\u5fc3\uff0cy \u5750\u6807");
    model.param().set("wm", "3[cm]", "\u53cd\u5c04\u955c\u5bbd\u5ea6");
    model.param().set("hm", "2[mm]", "\u53cd\u5c04\u955c\u9ad8\u5ea6");
    model.param()
         .set("xm1", "xc+(R+hm/2)*sin(phi0)", "\u53f3\u4e0b\u89d2\u53cd\u5c04\u955c\u4e2d\u5fc3\uff0cx \u5750\u6807");
    model.param()
         .set("ym1", "yc-(R+hm/2)*cos(phi0)", "\u53f3\u4e0b\u89d2\u53cd\u5c04\u955c\u4e2d\u5fc3\uff0cy \u5750\u6807");
    model.param()
         .set("xm2", "xc+(R+hm/2)*sin(phi0)", "\u53f3\u4e0a\u89d2\u53cd\u5c04\u955c\u4e2d\u5fc3\uff0cx \u5750\u6807");
    model.param()
         .set("ym2", "yc+(R+hm/2)*cos(phi0)", "\u53f3\u4e0a\u89d2\u53cd\u5c04\u955c\u4e2d\u5fc3\uff0cy \u5750\u6807");
    model.param().set("ws", "3[cm]", "\u5206\u675f\u5668\u5bbd\u5ea6");
    model.param().set("hs", "2[mm]", "\u5206\u675f\u5668\u9ad8\u5ea6");
    model.param().set("L0x", "cos(phi0)", "\u521d\u59cb\u5c04\u7ebf\u65b9\u5411\uff0cx \u5206\u91cf");
    model.param().set("L0y", "sin(phi0)", "\u521d\u59cb\u5c04\u7ebf\u65b9\u5411\uff0cy \u5206\u91cf");
    model.param().set("q0x", "-5[cm]*L0x", "\u5c04\u7ebf\u91ca\u653e\uff0cx \u5750\u6807");
    model.param().set("q0y", "-5[cm]*L0y", "\u5c04\u7ebf\u91ca\u653e\uff0cy \u5750\u6807");
    model.param().set("Lo", "3[cm]", "\u5230\u969c\u788d\u7269\u7684\u8ddd\u79bb");
    model.param().set("wo", "3[cm]", "\u969c\u788d\u7269\u5bbd\u5ea6");
    model.param().set("ho", "2[mm]", "\u969c\u788d\u7269\u9ad8\u5ea6");
    model.param().set("xo", "-(Lo+ho/2)*L0x", "\u969c\u788d\u7269\u4e2d\u5fc3\uff0cx \u5750\u6807");
    model.param().set("yo", "(Lo+ho/2)*L0y", "\u969c\u788d\u7269\u4e2d\u5fc3\uff0cy \u5750\u6807");
    model.param().set("dphi", "8*pi*A/(c_const*lam0)*Omega", "\u9884\u8ba1\u76f8\u79fb");
    model.param().set("zf", "4*A/(c_const*lam0)*Omega", "\u9884\u8ba1\u6761\u7eb9\u504f\u79fb\u91cf");
    model.param().set("dt_exp", "4*A*Omega/c_const^2", "\u9884\u8ba1\u6e21\u8d8a\u65f6\u95f4\u5dee");
    model.param().set("dL_exp", "dt_exp*c_const", "\u9884\u8ba1\u5149\u7a0b\u5dee");
    model.param().set("nu", "c_const/lam0", "\u9891\u7387");
    model.param().set("dnu_exp", "4*A*Omega/(lam0*P)", "\u9884\u8ba1\u62cd\u9891");
    model.param().set("SF_exp", "4*A/(lam0*P)", "\u9884\u8ba1\u6bd4\u4f8b\u56e0\u5b50");
    model.param().set("Omega_t", "P*eps*c_const/(4*A)", "\u6d88\u9664\u8bef\u5dee\u7684\u9608\u503c");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").label("\u53f3\u4e0b\u53cd\u5c04\u955c");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"wm", "hm"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"xm1", "ym1"});
    model.component("comp1").geom("geom1").feature("r1").set("rot", 30);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").label("\u53f3\u4e0a\u53cd\u5c04\u955c");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"wm", "hm"});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"xm2", "ym2"});
    model.component("comp1").geom("geom1").feature("r2").set("rot", 150);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").label("\u5206\u675f\u5668");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"ws", "hs"});
    model.component("comp1").geom("geom1").feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", "hs/2", 0);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").label("\u969c\u788d\u7269");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"wo", "ho"});
    model.component("comp1").geom("geom1").feature("r4").set("base", "center");
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"xo", "yo"});
    model.component("comp1").geom("geom1").feature("r4").set("rot", 60);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").selection().all();

    model.component("comp1").geom("geom1").run();

    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("angularVelocity", "Omega");
    model.component("comp1").common("rot1").set("rotationAxisBasePoint", new String[]{"xc", "yc", "0"});

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("L1", "gop.gopop1(if(gop.pidx==1,gop.L,0))", "\u5149\u7a0b\u957f\u5ea6\uff0c\u7b2c\u4e00\u5c04\u7ebf");
    model.component("comp1").variable("var1")
         .set("L4", "gop.gopop1(if(gop.pidx==4,gop.L,0))", "\u5149\u7a0b\u957f\u5ea6\uff0c\u7b2c\u56db\u5c04\u7ebf");
    model.component("comp1").variable("var1").set("dL", "L4-L1", "\u5149\u7a0b\u5dee");
    model.component("comp1").variable("var1").set("dnu", "dL/P*nu", "\u62cd\u9891");
    model.component("comp1").variable("var1").set("SF", "dnu/Omega", "\u6bd4\u4f8b\u56e0\u5b50");

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 3, 0);
    model.component("comp1").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputeIntensity", 0);
    model.component("comp1").physics("gop").prop("ComputeOpticalPathLength")
         .setIndex("ComputeOpticalPathLength", 1, 0);
    model.component("comp1").physics("gop").feature("op1").set("lambda0", "lam0");
    model.component("comp1").physics("gop").create("matd2", "MaterialDiscontinuity", 1);
    model.component("comp1").physics("gop").feature("matd2").selection().set(6, 9);
    model.component("comp1").physics("gop").feature("matd2").set("ReleaseReflectedRays", "Never");
    model.component("comp1").physics("gop").feature("matd2").label("ARC");
    model.component("comp1").physics("gop").create("matd3", "MaterialDiscontinuity", 1);
    model.component("comp1").physics("gop").feature("matd3").selection().set(8);
    model.component("comp1").physics("gop").feature("matd3").label("\u5206\u675f\u5668");
    model.component("comp1").physics("gop").feature("matd3")
         .set("ThinDielectricFilmsOnBoundary", "SpecifyReflectance");
    model.component("comp1").physics("gop").feature("matd3").set("Rf", 0.5);
    model.component("comp1").physics("gop").create("mir1", "Mirror", 1);
    model.component("comp1").physics("gop").feature("mir1").selection().set(13, 15);
    model.component("comp1").physics("gop").feature("mir1").label("\u53cd\u5c04\u955c");
    model.component("comp1").physics("gop").create("wall1", "Wall", 1);
    model.component("comp1").physics("gop").feature("wall1").selection().set(3);
    model.component("comp1").physics("gop").feature("wall1").label("\u969c\u788d\u7269");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", "q0x", 0);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", "q0y", 1);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new String[]{"L0x", "L0y", "0"});
    model.component("comp1").physics("gop").create("rt1", "RayTermination", -1);
    model.component("comp1").physics("gop").feature("rt1")
         .set("SpatialExtentsOfRayPropagation", "BoundingBoxFromGeometry");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"1.5"});

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Omega", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad/s", 0);
    model.study("std1").feature("param").setIndex("pname", "Omega", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad/s", 0);
    model.study("std1").feature("param").setIndex("plistarr", "10^{range(0,0.2,5)}", 0);
    model.study("std1").feature("param").setIndex("punit", "deg/h", 0);
    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("lunit", "cm");
    model.study("std1").feature("rtrac").set("llist", "0 1.5*P");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol2");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").setIndex("looplevel", 26, 1);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.I");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.pidx");
    model.result("pg1").feature("rtrj1").feature("col1").set("descr", "\u5c04\u7ebf\u6307\u6570");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u62cd\u9891");
    model.result("pg2").set("data", "ray1");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"dnu"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u62cd\u9891"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"1/s"});
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg2").feature("glob1").set("linemarker", "point");
    model.result("pg2").run();
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("ylog", true);
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u6bd4\u4f8b\u56e0\u5b50");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("expr", new String[]{"SF"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u6bd4\u4f8b\u56e0\u5b50"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"rad"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"SF", "SF_exp"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u6bd4\u4f8b\u56e0\u5b50", "\u9884\u8ba1\u6bd4\u4f8b\u56e0\u5b50"});
    model.result("pg3").run();

    model.title("\u8428\u683c\u7eb3\u514b\u5e72\u6d89\u4eea");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u7b80\u5355\u7684\u8428\u683c\u7eb3\u514b\u5e72\u6d89\u4eea\u6a21\u578b\uff0c\u7531\u4e24\u4e2a\u53cd\u5c04\u955c\u548c\u4e00\u4e2a\u5206\u675f\u5668\u6309\u4e09\u89d2\u5f62\u6392\u5217\u800c\u6210\u3002\u6574\u4e2a\u5efa\u6a21\u57df\u53d1\u751f\u65cb\u8f6c\uff1b\u7531\u4e8e\u4ea7\u751f\u4e86\u8428\u683c\u7eb3\u514b\u6548\u5e94\uff0c\u4e09\u89d2\u5f62\u4e2d\u6cbf\u76f8\u53cd\u65b9\u5411\u4f20\u64ad\u7684\u5149\u7ebf\u5177\u6709\u4e0d\u540c\u7684\u5149\u7a0b\u3002\u6211\u4eec\u53ef\u4ee5\u636e\u6b64\u63a8\u5bfc\u7cfb\u7edf\u7684\u89d2\u901f\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();

    model.label("sagnac_interferometer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
