/*
 * heart_electrical.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class heart_electrical {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Equation_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("g", "GeneralFormPDE", "geom1");
    model.component("comp1").physics("g").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("g").field("dimensionless").component(new String[]{"u1", "u2"});
    model.component("comp1").physics("g").feature("gfeq1")
         .set("Ga", new String[][]{{"-u1x", "-u1y", "-u1z"}, {"-u2x", "-u2y", "-u2z"}});

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/g", true);

    model.baseSystem("none");

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", 54);
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("elp1", "Ellipsoid");
    model.component("comp1").geom("geom1").feature("elp1").set("semiaxes", new int[]{54, 54, 70});
    model.component("comp1").geom("geom1").run("elp1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{110, 110, 110});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{0, 0, 55});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("elp1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new int[]{110, 110, 110});
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new int[]{0, 0, -55});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("sph1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("blk2");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("dif1", "dif2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("sca1", "Scale");
    model.component("comp1").geom("geom1").feature("sca1").set("keep", true);
    model.component("comp1").geom("geom1").feature("sca1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("sca1").set("isotropic", "2/3");
    model.component("comp1").geom("geom1").run("sca1");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").create("dif3", "Difference");
    model.component("comp1").geom("geom1").feature("dif3").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("dif3").selection("input2").set("sca1");
    model.component("comp1").geom("geom1").run("dif3");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 45);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 10);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new int[]{-5, 0, -5});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("cyl1").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("cyl1", "dif3");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").runPre("fin");

    model.param().set("alpha", "0.1");
    model.param().descr("alpha", "\u6fc0\u52b1\u9608\u503c");
    model.param().set("epsilon", "0.01");
    model.param().descr("epsilon", "\u5e94\u6fc0\u6027");
    model.param().set("beta", "0.5");
    model.param().descr("beta", "\u7cfb\u7edf\u53c2\u6570");
    model.param().set("gamma", "1");
    model.param().descr("gamma", "\u7cfb\u7edf\u53c2\u6570");
    model.param().set("delta", "0");
    model.param().descr("delta", "\u7cfb\u7edf\u53c2\u6570");
    model.param().set("V0", "1");
    model.param().descr("V0", "\u8ba1\u7b97\u7684\u7535\u52bf\u503c");
    model.param().set("nu0", "0.3");
    model.param().descr("nu0", "\u8ba1\u7b97\u7684\u6291\u5236\u503c");
    model.param().set("d", "1e-5");
    model.param().descr("d", "\u79bb\u8f74\u504f\u79fb\u8ddd\u79bb");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("g").feature("gfeq1").setIndex("Ga", new String[]{"0", "-u2y", "-u2z"}, 1);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("Ga", new String[]{"0", "0", "-u2z"}, 1);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("Ga", new int[]{0, 0, 0}, 1);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("f", "(alpha-u1)*(u1-1)*u1-u2", 0);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("f", "epsilon*(beta*u1-gamma*u2-delta)", 1);
    model.component("comp1").physics("g").feature("init1").set("u1", "V0*((x+d)>0)*((z+d)>0)");
    model.component("comp1").physics("g").feature("init1").set("u2", "nu0*((-x+d)>0)*((z+d)>0)");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,5,500)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").label("\u4e00\u822c\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg1").feature("slc1").set("expr", "u1");
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(1, 3, 10, 11, 12, 13, 14, 15, 16, 17, 18);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u663e\u5f0f 1");
    model.component("comp1").selection("sel1").set(1, 3, 10, 11, 12, 13, 14, 15, 16, 17, 18);

    model.result().dataset("dset1").selection().named("sel1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 25, 0);
    model.result("pg2").run();

    model.component("comp1").physics().create("g2", "GeneralFormPDE", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/g2", false);

    model.component("comp1").physics("g2").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("g2").field("dimensionless").field("v");
    model.component("comp1").physics("g2").field("dimensionless").component(new String[]{"v1", "v2"});
    model.component("comp1").physics("g2").feature("gfeq1")
         .set("Ga", new String[][]{{"-v1x", "-v1y", "-v1z"}, {"-v2x", "-v2y", "-v2z"}});

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/g", false);
    model.study("std2").feature("time").setSolveFor("/physics/g2", true);

    model.param().set("c1", "2");
    model.param().descr("c1", "\u504f\u5fae\u5206\u65b9\u7a0b\u53c2\u6570");
    model.param().set("c3", "-0.2");
    model.param().descr("c3", "\u504f\u5fae\u5206\u65b9\u7a0b\u53c2\u6570");

    model.component("comp1").physics("g2").prop("ShapeProperty").set("shapeFunctionType", "shherm");
    model.component("comp1").physics("g2").feature("gfeq1")
         .setIndex("Ga", new String[]{"-v1x+c1*v2x", "-v1y", "-v1z"}, 0);
    model.component("comp1").physics("g2").feature("gfeq1")
         .setIndex("Ga", new String[]{"-v1x+c1*v2x", "-v1y+c1*v2y", "-v1z"}, 0);
    model.component("comp1").physics("g2").feature("gfeq1")
         .setIndex("Ga", new String[]{"-v1x+c1*v2x", "-v1y+c1*v2y", "-v1z+c1*v2z"}, 0);
    model.component("comp1").physics("g2").feature("gfeq1")
         .setIndex("Ga", new String[]{"-c1*v1x-v2x", "-v2y", "-v2z"}, 1);
    model.component("comp1").physics("g2").feature("gfeq1")
         .setIndex("Ga", new String[]{"-c1*v1x-v2x", "-c1*v1y-v2y", "-v2z"}, 1);
    model.component("comp1").physics("g2").feature("gfeq1")
         .setIndex("Ga", new String[]{"-c1*v1x-v2x", "-c1*v1y-v2y", "-c1*v1z-v2z"}, 1);
    model.component("comp1").physics("g2").feature("gfeq1").setIndex("f", "v1-(v1-c3*v2)*(v1^2+v2^2)", 0);
    model.component("comp1").physics("g2").feature("gfeq1").setIndex("f", "v2-(c3*v1+v2)*(v1^2+v2^2)", 1);
    model.component("comp1").physics("g2").feature("init1").set("v1", "tanh(z[1/m])");
    model.component("comp1").physics("g2").feature("init1").set("v2", "-tanh(z[1/m])");

    model.study("std2").feature("time").set("tlist", "range(0,5,75)");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", 0.001);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol2").feature("t1").set("atolglobal", "0.0001");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 16, 0);
    model.result("pg3").create("slc1", "Slice");
    model.result("pg3").label("\u4e00\u822c\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b 2");
    model.result("pg3").feature("slc1").set("expr", "v1");
    model.result("pg3").run();
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().named("sel1");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("edges", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "v1");
    model.result("pg4").feature("surf1").set("descr", "\u56e0\u53d8\u91cf v1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 10, 0);
    model.result("pg4").run();

    model.title("\u5fc3\u810f\u7535\u4fe1\u53f7");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u4e24\u4e2a\u6570\u5b66\u6a21\u578b\uff0c\u5206\u522b\u57fa\u4e8e\u83f2\u8328\u4f11-\u5357\u4e91\u548c\u590d\u91d1\u5179\u5821-\u6717\u9053\u65b9\u7a0b\uff0c\u4ee5\u63cf\u8ff0\u5fc3\u810f\u7ec4\u7ec7\u4e2d\u7535\u4fe1\u53f7\u4f20\u64ad\u7684\u5404\u4e2a\u65b9\u9762\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("heart_electrical.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
