/*
 * alvarez_lens.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:15 by COMSOL 6.3.0.290. */
public class alvarez_lens {

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

    model.param().set("rn", "20[mm]");
    model.param().descr("rn", "Normalizing radius");
    model.param().set("thickness", "5[mm]");
    model.param().descr("thickness", "Central thickness");
    model.param().set("a", "0.43[mm]");
    model.param().descr("a", "Surface coefficient");
    model.param().set("nref", "1.505");
    model.param().descr("nref", "Refractive index");
    model.param().set("dx", "0[mm]");
    model.param().descr("dx", "Displacement in x");
    model.param().set("dy", "-4[mm]");
    model.param().descr("dy", "Displacement in y");
    model.param().set("dz", "2[mm]");
    model.param().descr("dz", "Displacement in z");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "rn");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "thickness");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "-thickness/2"});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("ps1", "ParametricSurface");
    model.component("comp1").geom("geom1").feature("ps1").set("parmin1", -1);
    model.component("comp1").geom("geom1").feature("ps1").set("parmin2", -1);
    model.component("comp1").geom("geom1").feature("ps1")
         .set("coord", new String[]{"s1*rn", "s2*rn", "6*sqrt(8)*a*(s1^2*s2+s2^3/3) - 3*sqrt(8)*a*s2"});
    model.component("comp1").geom("geom1").run("ps1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("ps1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("spl1", "Split");
    model.component("comp1").geom("geom1").feature("spl1").selection("input").set("par1");
    model.component("comp1").geom("geom1").run("spl1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("spl1(2)");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "dx");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "dy");
    model.component("comp1").geom("geom1").feature("mov1").set("displz", "dz");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("mov2").selection("input").set("spl1(1)");
    model.component("comp1").geom("geom1").feature("mov2").set("displx", "-dx");
    model.component("comp1").geom("geom1").feature("mov2").set("disply", "-dy");
    model.component("comp1").geom("geom1").feature("mov2").set("displz", "-dz");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"nref"});

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").feature("matd1").set("ReleaseReflectedRays", "Never");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("GridType", "Hexapolar");
    model.component("comp1").physics("gop").feature("relg1").set("qcc", new String[]{"0", "0", "-2*thickness"});
    model.component("comp1").physics("gop").feature("relg1").set("rcc", new int[]{0, 0, 1});
    model.component("comp1").physics("gop").feature("relg1").set("Rc", "rn/3");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Ncr", 10, 0);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new int[]{0, 0, 1});

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("tlist", "range(0,0.01,0.6)");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "rn", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "rn", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "dy", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(-10, 1, -4)", 0);
    model.study("std1").feature("param").setIndex("punit", "mm", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol2");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 61, 0);
    model.result("pg1").setIndex("looplevel", 7, 1);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").stepFirst(1);
    model.result("pg1").run();
    model.result("pg1").set("data", "none");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").set("arrayaxis", "y");
    model.result("pg1").feature("rtrj1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").set("data", "ray1");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.rrms");
    model.result("pg1").run();
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("arraydim", "1");
    model.result("pg1").feature("vol1").set("data", "ray1");
    model.result("pg1").feature("vol1").set("manualindexing", true);
    model.result("pg1").feature("rtrj1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("rtrj2", "rtrj1");
    model.result("pg1").feature("rtrj2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj2").setIndex("looplevel", 4, 1);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("vol2", "vol1");
    model.result("pg1").feature("vol2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").setIndex("looplevel", 4, 1);
    model.result("pg1").feature("vol2").set("arrayindex", 1);
    model.result("pg1").run();
    model.result("pg1").feature("rtrj2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("rtrj3", "rtrj2");
    model.result("pg1").feature("rtrj3").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj3").setIndex("looplevel", 1, 1);
    model.result("pg1").run();
    model.result("pg1").feature("vol2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("vol3", "vol2");
    model.result("pg1").feature("vol3").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("vol3").setIndex("looplevel", 1, 1);
    model.result("pg1").feature("vol3").set("arrayindex", 2);
    model.result("pg1").run();

    model.view("view2").set("showaxisorientation", false);
    model.view("view2").set("showgrid", false);

    model.result().dataset().create("ip1", "IntersectionPoint3D");
    model.result().dataset("ip1").setIndex("looplevelinput", "manual", 1);
    model.result().dataset("ip1").setIndex("looplevel", new int[]{7}, 1);
    model.result().dataset("ip1").set("genpnpoint", new double[]{0, 0, 139.63});
    model.result().dataset().duplicate("ip2", "ip1");
    model.result().dataset("ip2").setIndex("looplevel", new int[]{4}, 1);
    model.result().dataset("ip2").set("genpnpoint", new double[]{0, 0, 79.32});
    model.result().dataset().duplicate("ip3", "ip2");
    model.result().dataset("ip3").setIndex("looplevel", new int[]{1}, 1);
    model.result().dataset("ip3").set("genpnpoint", new double[]{0, 0, 55.67});
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").set("data", "none");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("spot1", "SpotDiagram");
    model.result("pg2").feature("spot1").set("data", "ip1");
    model.result("pg2").run();
    model.result("pg2").feature("spot1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("spot1").feature("col1").set("expr", "at(0, gop.rrel)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("spot2", "spot1");
    model.result("pg2").run();
    model.result("pg2").feature("spot2").set("data", "ip2");
    model.result("pg2").feature("spot2").set("posexpr", new String[]{"0.2", "0"});
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("spot3", "spot2");
    model.result("pg2").run();
    model.result("pg2").feature("spot3").set("data", "ip3");
    model.result("pg2").feature("spot3").set("posexpr", new String[]{"0.4", "0"});
    model.result("pg2").run();

    model.title("Alvarez \u900f\u955c");

    model
         .description("\u968f\u7740\u5236\u9020\u548c\u52a0\u5de5\u6280\u672f\u7684\u8fdb\u6b65\uff0c\u81ea\u7531\u66f2\u9762\u5149\u5b66\u518d\u6b21\u6fc0\u53d1\u4e86\u7814\u7a76\u8005\u7684\u5174\u8da3\u3002\u672c\u6a21\u578b\u7814\u7a76\u4e00\u79cd\u540d\u4e3a Alvarez \u900f\u955c\u7684\u7279\u5b9a\u81ea\u7531\u66f2\u9762\u8bbe\u8ba1\uff0c\u5176\u4e2d\u4e24\u4e2a\u4e92\u8865\u7684\u7acb\u65b9\u4f53\u8868\u9762\u76f8\u5bf9\u4e8e\u5f7c\u6b64\u6a2a\u5411\u79fb\u52a8\uff0c\u4ee5\u5b9e\u73b0\u53ef\u53d8\u5149\u5b66\u529f\u7387\u3002");

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

    model.label("alvarez_lens.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
