/*
 * s_bend_benchmark.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:17 by COMSOL 6.3.0.290. */
public class s_bend_benchmark {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Molecular_Flow_Module\\Benchmarks");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("fmf", "FreeMolecularFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fmf", true);

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{2, 1});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new int[]{2, 2});
    model.component("comp1").geom("geom1").feature("c1").set("rot", 270);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("c2").set("r", 2);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("c3").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c3").set("pos", new int[]{5, 2});
    model.component("comp1").geom("geom1").feature("c3").set("rot", 90);
    model.component("comp1").geom("geom1").run("c3");
    model.component("comp1").geom("geom1").feature().duplicate("c4", "c3");
    model.component("comp1").geom("geom1").feature("c4").set("r", 2);
    model.component("comp1").geom("geom1").run("c4");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("c4");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("c3");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{2, 1});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{5, 3});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("dif1", "dif2", "r1", "r2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").runPre("fin");

    model.param().set("T0", "293.15[K]");
    model.param().descr("T0", "\u6e29\u5ea6");
    model.param().set("Mw", "0.028[kg/mol]");
    model.param().descr("Mw", "\u5206\u5b50\u91cf");
    model.param().set("p_in", "1E-3[Pa]");
    model.param().descr("p_in", "\u5165\u53e3\u538b\u529b");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("fmf").feature("fmfp1").setIndex("Mn_G", "Mw", 0);
    model.component("comp1").physics("fmf").feature("st1").set("T", "T0");
    model.component("comp1").physics("fmf").create("res1", "Reservoir", 1);
    model.component("comp1").physics("fmf").feature("res1").selection().set(1);
    model.component("comp1").physics("fmf").feature("res1").setIndex("p0", "p_in", 0);
    model.component("comp1").physics("fmf").create("tv1", "TotalVacuum", 1);
    model.component("comp1").physics("fmf").feature("tv1").selection().set(6);
    model.component("comp1").physics("fmf").create("ndr1", "NumberDensityReconDomain", 2);
    model.component("comp1").physics("fmf").feature("ndr1").selection().set(1);
    model.component("comp1").physics("fmf").prop("Compute").set("ComputeP", false);
    model.component("comp1").physics("fmf").prop("IntegrationProperty").set("IntegrationResolution", 512);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(6);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Jin", "intop1(fmf.J_G)");
    model.component("comp1").variable("var1").descr("Jin", "\u6d41\u5165");
    model.component("comp1").variable("var1").set("Jout", "intop2(G)");
    model.component("comp1").variable("var1").descr("Jout", "\u6d41\u51fa");
    model.component("comp1").variable("var1").set("alpha", "Jout/Jin");
    model.component("comp1").variable("var1").descr("alpha", "\u4f20\u8f93\u6982\u7387");

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u5165\u5c04\u5206\u5b50\u901a\u91cf (fmf)");
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").label("\u7ebf");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("expr", "fmf.Gtot");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("resolution", "norefine");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u603b\u6570\u5bc6\u5ea6 (fmf)");
    model.result("pg2").feature().create("line1", "Line");
    model.result("pg2").feature("line1").label("\u7ebf");
    model.result("pg2").feature("line1").set("showsolutionparams", "on");
    model.result("pg2").feature("line1").set("expr", "fmf.ntot");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("resolution", "norefine");
    model.result("pg2").feature("line1").set("smooth", "internal");
    model.result("pg2").feature("line1").set("showsolutionparams", "on");
    model.result("pg2").feature("line1").set("data", "parent");
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u6570\u5bc6\u5ea6 (fmf)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "fmf.n_G");
    model.result("pg3").feature("surf1").set("descr", "\u6570\u5bc6\u5ea6");
    model.result("pg3").run();
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
    model.param().set("J_in", "p_in*N_A_const/sqrt(2*R_const*T0*Mw*pi)");
    model.param().descr("J_in", "\u53d1\u5c04\u5206\u5b50\u901a\u91cf");
    model.param().set("L", "0.01[m]");
    model.param().descr("L", "\u5165\u53e3\u957f\u5ea6");
    model.param().set("Np", "10000");
    model.param().descr("Np", "\u9897\u7c92\u6570");

    model.component("comp1").physics("pt").create("tre1", "ThermalReEmission", 1);
    model.component("comp1").physics("pt").feature("tre1").selection().all();
    model.component("comp1").physics("pt").feature("tre1").set("T", "T0");
    model.component("comp1").physics("pt").create("out1", "Outlet", 1);
    model.component("comp1").physics("pt").feature("out1").selection().set(6);
    model.component("comp1").physics("pt").create("inl1", "Inlet", 1);
    model.component("comp1").physics("pt").feature("inl1").selection().set(1);
    model.component("comp1").physics("pt").feature("inl1").set("InitialPosition", "Density");
    model.component("comp1").physics("pt").feature("inl1").setIndex("N", "Np", 0);
    model.component("comp1").physics("pt").feature("inl1").set("VelocitySpecification", "Thermal");
    model.component("comp1").physics("pt").feature("inl1").set("T", "T0");
    model.component("comp1").physics("pt").create("wall2", "Wall", 1);
    model.component("comp1").physics("pt").feature("wall2").selection().set(1);
    model.component("comp1").physics("pt").feature("wall2").set("WallCondition", "Disappear");
    model.component("comp1").physics("pt").create("pcnt1", "ParticleCounter", 1);
    model.component("comp1").physics("pt").feature("pcnt1").selection().set(6);
    model.component("comp1").physics("pt").feature("pcnt1").set("ReleaseFeature", "inl1");
    model.component("comp1").physics("pt").feature("pp1").set("mp", "M");
    model.component("comp1").physics("pt").create("dacc1", "DomainAccumulator", 2);
    model.component("comp1").physics("pt").feature("dacc1").selection().set(1);
    model.component("comp1").physics("pt").feature("dacc1").set("AccumulateOver", "ElementsAndTime");
    model.component("comp1").physics("pt").feature("dacc1").set("AccumulatedVariableName", "Nd");
    model.component("comp1").physics("pt").feature("dacc1").set("DependentVariableQuantity", "numberdensity");
    model.component("comp1").physics("pt").feature("dacc1").set("R", "J_in*L/Np");

    model.study("std2").feature("time").set("tlist", "range(0,2.0689655172413793e-4,0.006)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_pt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "pt");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "part1");
    model.result("pg4").setIndex("looplevel", 30, 0);
    model.result("pg4").label("\u7c92\u5b50\u8f68\u8ff9 (pt)");
    model.result("pg4").create("traj1", "ParticleTrajectories");
    model.result("pg4").feature("traj1").set("pointtype", "point");
    model.result("pg4").feature("traj1").set("linetype", "none");
    model.result("pg4").feature("traj1").create("col1", "Color");
    model.result("pg4").feature("traj1").feature("col1").set("expr", "pt.V");
    model.result("pg4").run();
    model.result("pg2").run();
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
    model.result("pg3").run();
    model.result().duplicate("pg5", "pg3");
    model.result("pg5").run();
    model.result("pg5").label("\u6570\u5bc6\u5ea6\u6bd4\u8f83");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("plotarrayenable", true);
    model.result("pg5").set("arrayaxis", "y");
    model.result("pg5").set("relpadding", 0);
    model.result("pg5").create("surf2", "Surface");
    model.result("pg5").feature("surf2").set("arraydim", "1");
    model.result("pg5").feature("surf2").set("data", "part1");
    model.result("pg5").feature("surf2").set("expr", "pt.Nd");
    model.result("pg5").feature("surf2").set("resolution", "norefine");
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").run();

    model.title("S \u5f2f\u4e2d\u7684\u5206\u5b50\u6d41");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u4e00\u4e2a S \u5f2f\u51e0\u4f55\u4e2d\u7684\u4f20\u8f93\u6982\u7387\uff0c\u4f7f\u7528\u4e86\u201c\u81ea\u7531\u5206\u5b50\u6d41\u201d\u4e2d\u7684\u89d2\u7cfb\u6570\u6cd5\uff0c\u4ee5\u53ca\u201c\u6570\u5b66\u7c92\u5b50\u8ffd\u8e2a\u201d\u63a5\u53e3\u4e2d\u7684\u8499\u7279\u5361\u7f57\u65b9\u6cd5\u3002\u4e24\u79cd\u65b9\u6cd5\u8ba1\u7b97\u5f97\u5230\u7684\u4f20\u8f93\u6982\u7387\u57fa\u672c\u4e00\u81f4\uff0c\u8bef\u5dee\u5c0f\u4e8e 1%\u3002\n\n\u672c\u4f8b\u9700\u8981\u201c\u7c92\u5b50\u8ffd\u8e2a\u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("s_bend_benchmark.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
