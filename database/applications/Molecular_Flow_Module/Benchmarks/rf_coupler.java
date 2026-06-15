/*
 * rf_coupler.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:16 by COMSOL 6.3.0.290. */
public class rf_coupler {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Molecular_Flow_Module\\Benchmarks");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("fmf", "FreeMolecularFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fmf", true);

    model.param().set("T0", "293.15[K]");
    model.param().descr("T0", "\u6e29\u5ea6");
    model.param().set("Mw", "0.028[kg/mol]");
    model.param().descr("Mw", "\u5206\u5b50\u91cf");
    model.param().set("p0", "1E-4[Pa]");
    model.param().descr("p0", "\u5165\u53e3\u538b\u529b");
    model.param().set("frac", "0.7");
    model.param().descr("frac", "\u6cf5\u5165\u5206\u5b50\u7684\u5206\u6570");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yx");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{2, 8});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new int[]{1, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("x", "1 3 3 7 7 3 3 1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("y", "8 35 35 35 35 8 8 8");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new int[]{4, 15});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new int[]{3, 35});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("pol1", "r1", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle1", -90);
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", 90);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 4);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 10);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new int[]{42, 5, 0});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "y");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "rev1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("fin", 11);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("fmf").feature("fmfp1").setIndex("Mn_G", "Mw", 0);
    model.component("comp1").physics("fmf").feature("st1").set("T", "T0");
    model.component("comp1").physics("fmf").create("res1", "Reservoir", 2);
    model.component("comp1").physics("fmf").feature("res1").selection().set(1);
    model.component("comp1").physics("fmf").feature("res1").setIndex("p0", "p0", 0);
    model.component("comp1").physics("fmf").create("pmp1", "VacuumPump", 2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").physics("fmf").feature("pmp1").selection().set(18);
    model.component("comp1").physics("fmf").feature("pmp1").setIndex("frac", "frac", 0);
    model.component("comp1").physics("fmf").create("msym1", "Symmetry", -1);
    model.component("comp1").physics("fmf").feature("msym1").selection("FirstReflectionPlane").set(2, 5);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().set(18);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u5206\u5b50\u6d41\u53d8\u91cf");
    model.component("comp1").variable("var1").set("Jin", "intop1(fmf.J_G)");
    model.component("comp1").variable("var1").descr("Jin", "\u6d41\u5165");
    model.component("comp1").variable("var1").set("Jout", "intop2(G-fmf.J_G)");
    model.component("comp1").variable("var1").descr("Jout", "\u6d41\u51fa\uff0c\u6cf5 1");
    model.component("comp1").variable("var1").set("alpha", "Jout/Jin");
    model.component("comp1").variable("var1").descr("alpha", "\u4f20\u8f93\u6982\u7387");

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u5165\u5c04\u5206\u5b50\u901a\u91cf (fmf)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "fmf.Gtot");
    model.result("pg1").feature("surf1").set("resolution", "norefine");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u603b\u6570\u5bc6\u5ea6 (fmf)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "fmf.ntot");
    model.result("pg2").feature("surf1").set("resolution", "norefine");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u603b\u538b (fmf)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "fmf.ptot");
    model.result("pg3").feature("surf1").set("resolution", "norefine");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "zx");
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"alpha"});
    model.result().numerical("gev1").set("descr", new String[]{"\u4f20\u8f93\u6982\u7387"});
    model.result().numerical("gev1").set("unit", new String[]{"1"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.component("comp1").physics().create("pt", "MathParticle", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/pt", false);
    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/fmf", false);
    model.study("std2").feature("time").setSolveFor("/physics/pt", true);

    model.component("comp1").physics("pt").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);

    model.param().set("M", "Mw/N_A_const");
    model.param().descr("M", "\u9897\u7c92\u8d28\u91cf");

    model.component("comp1").physics("pt").feature("pp1").set("mp", "M");
    model.component("comp1").physics("pt").create("inl1", "Inlet", 2);
    model.component("comp1").physics("pt").feature("inl1").selection().set(1);
    model.component("comp1").physics("pt").feature("inl1").set("InitialPosition", "ProjectedPlaneGrid");
    model.component("comp1").physics("pt").feature("inl1").setIndex("N", 10000, 0);
    model.component("comp1").physics("pt").feature("inl1").set("VelocitySpecification", "Thermal");
    model.component("comp1").physics("pt").feature("inl1").set("T", "T0");
    model.component("comp1").physics("pt").create("tre1", "ThermalReEmission", 2);
    model.component("comp1").physics("pt").feature("tre1").selection().all();
    model.component("comp1").physics("pt").feature("tre1").set("T", "T0");
    model.component("comp1").physics("pt").create("tre2", "ThermalReEmission", 2);
    model.component("comp1").physics("pt").feature("tre2").selection().set(18);
    model.component("comp1").physics("pt").feature("tre2").set("T", "T0");
    model.component("comp1").physics("pt").feature("tre2").set("gammaf", "frac");
    model.component("comp1").physics("pt").create("pcnt1", "ParticleCounter", 2);
    model.component("comp1").physics("pt").feature("pcnt1").selection().set(18);
    model.component("comp1").physics("pt").feature("pcnt1").set("ReleaseFeature", "inl1");
    model.component("comp1").physics("pt").create("wall2", "Wall", 2);
    model.component("comp1").physics("pt").feature("wall2").selection().set(1);
    model.component("comp1").physics("pt").feature("wall2").set("WallCondition", "Disappear");
    model.component("comp1").physics("pt").create("wall3", "Wall", 2);
    model.component("comp1").physics("pt").feature("wall3").selection().set(2, 5);
    model.component("comp1").physics("pt").feature("wall3").set("WallCondition", "Bounce");

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").autoMeshSize(2);
    model.component("comp1").mesh("mesh2").run();

    model.study("std2").feature("time").set("tlist", "range(0,0.03/49,0.03)");
    model.study("std2").feature("time").setEntry("mesh", "geom1", "mesh2");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("initialstepgenalphaactive", true);
    model.sol("sol2").feature("t1").set("initialstepgenalpha", "1e-8");
    model.sol("sol2").feature("t1").set("maxstepconstraintgenalpha", "const");
    model.sol("sol2").feature("t1").set("maxstepgenalpha", "1e-4");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_pt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "pt");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "part1");
    model.result("pg4").setIndex("looplevel", 50, 0);
    model.result("pg4").label("\u7c92\u5b50\u8f68\u8ff9 (pt)");
    model.result("pg4").create("traj1", "ParticleTrajectories");
    model.result("pg4").feature("traj1").set("pointtype", "point");
    model.result("pg4").feature("traj1").set("linetype", "none");
    model.result("pg4").feature("traj1").create("col1", "Color");
    model.result("pg4").feature("traj1").feature("col1").set("expr", "pt.V");
    model.result("pg4").run();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev2").set("expr", new String[]{"pt.pcnt1.alpha"});
    model.result().numerical("gev2").set("descr", new String[]{"\u4f20\u8f93\u6982\u7387"});
    model.result().numerical("gev2").set("unit", new String[]{"1"});
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result("pg4").run();

    model.title("RF \u8026\u5408\u5668\u4e2d\u7684\u5206\u5b50\u6d41");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97 RF \u8026\u5408\u5668\u4e2d\u7684\u4f20\u8f93\u6982\u7387\uff0c\u4f7f\u7528\u4e86\u201c\u81ea\u7531\u5206\u5b50\u6d41\u201d\u63a5\u53e3\u4e2d\u7684\u89d2\u7cfb\u6570\u6cd5\uff0c\u4ee5\u53ca\u201c\u6570\u5b66\u7c92\u5b50\u8ffd\u8e2a\u201d\u63a5\u53e3\u4e2d\u7684\u8499\u7279\u5361\u7f57\u65b9\u6cd5\u3002\u4e24\u79cd\u65b9\u6cd5\u8ba1\u7b97\u5f97\u5230\u7684\u4f20\u8f93\u6982\u7387\u57fa\u672c\u4e00\u81f4\uff0c\u8bef\u5dee\u5c0f\u4e8e 1%\u3002\n\n\u672c\u4f8b\u9700\u8981\u201c\u7c92\u5b50\u8ffd\u8e2a\u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("rf_coupler.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
