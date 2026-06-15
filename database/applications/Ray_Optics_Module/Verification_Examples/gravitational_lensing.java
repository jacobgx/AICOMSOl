/*
 * gravitational_lensing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:26 by COMSOL 6.3.0.290. */
public class gravitational_lensing {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.param().set("r0", "7E5[km]");
    model.param().descr("r0", "\u592a\u9633\u534a\u5f84");
    model.param().set("m0", "2E30[kg]");
    model.param().descr("m0", "\u592a\u9633\u8d28\u91cf");

    model.component("comp1").geom("geom1").lengthUnit("km");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "r0");
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"2E8", "1E7", "1E7"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0.5E8", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("r", "sqrt(x^2+y^2+z^2+eps)");
    model.component("comp1").variable("var1")
         .descr("r", "\u8ddd\u592a\u9633\u4e2d\u5fc3\u7684\u5f84\u5411\u8ddd\u79bb");
    model.component("comp1").variable("var1").set("n", "1+2*G_const*m0/(c_const^2*r)");
    model.component("comp1").variable("var1").descr("n", "\u6298\u5c04\u7387");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n"});

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", "-0.5E8", 0);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", "-7.01E5 7.01E5", 1);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new int[]{1, 0, 0});

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("lunit", "km");
    model.study("std1").feature("rtrac").set("llist", "range(0,2020202.0202020202,200000000)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("maxstepconstraintgenalpha", "const");
    model.sol("sol1").feature("t1").set("maxstepgenalpha", 1);

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
    model.result("pg1").setIndex("looplevel", 100, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "asin(gop.niy)");
    model.result("pg1").feature("rtrj1").feature("col1").set("unit", "arcsec");
    model.result("pg1").run();
    model.result().numerical().create("ray1", "Ray");
    model.result().numerical("ray1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("ray1").set("expr", "asin(gop.niy)");
    model.result().numerical("ray1").set("unit", "arcsec");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5c04\u7ebf\u8ba1\u7b97 1");
    model.result().numerical("ray1").set("table", "tbl1");
    model.result().numerical("ray1").setResult();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u504f\u79bb\u521d\u59cb\u65b9\u5411");
    model.result("pg2").set("data", "ray1");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u504f\u8f6c\u89d2\uff08\u5355\u4f4d\u4e3a\u89d2\u79d2\uff09");
    model.result("pg2").create("rtp1", "Ray1D");
    model.result("pg2").feature("rtp1").set("markerpos", "datapoints");
    model.result("pg2").feature("rtp1").set("linewidth", "preference");
    model.result("pg2").feature("rtp1").set("expr", "abs(asin(gop.niy))");
    model.result("pg2").feature("rtp1").set("unit", "arcsec");
    model.result("pg2").run();
    model.result("pg2").feature("rtp1").set("dataseries", "average");
    model.result("pg2").run();

    model.title("\u5f15\u529b\u900f\u955c");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u4ece\u5730\u7403\u4e0a\u89c2\u5bdf\u5230\u7684\u592a\u9633\u5982\u4f55\u5bfc\u81f4\u63a0\u8fc7\u5176\u8868\u9762\u7684\u5149\u7ebf\u53d1\u751f 1.75\u00a0\u89d2\u79d2\u7684\u504f\u8f6c\u3002\u7231\u56e0\u65af\u5766\u5728\u7b2c\u4e00\u6b21\u4e16\u754c\u5927\u6218\u671f\u95f4\u5b8c\u5584\u4ed6\u7684\u76f8\u5bf9\u8bba\u540e\u9884\u6d4b\u4e86\u8fd9\u4e2a\u503c\u3002");

    model.label("gravitational_lensing.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
