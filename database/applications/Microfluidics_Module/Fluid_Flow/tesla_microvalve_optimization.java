/*
 * tesla_microvalve_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:08 by COMSOL 6.3.0.290. */
public class tesla_microvalve_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("spf2", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf2", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");

    model.param().set("Re", "100");
    model.param().descr("Re", "\u96f7\u8bfa\u6570");
    model.param().set("D", "0.2[mm]");
    model.param().descr("D", "\u7279\u5f81\u5c3a\u5bf8");
    model.param().set("L", "5*D");
    model.param().descr("L", "\u901a\u9053\u957f\u5ea6");
    model.param().set("H", "1.75*D");
    model.param().descr("H", "\u6d41\u9053\u5bbd\u5ea6");
    model.param().set("mu0", "1E-3[Pa*s]");
    model.param().descr("mu0", "\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("rho0", "1E3[kg/m^3]");
    model.param().descr("rho0", "\u5bc6\u5ea6");
    model.param().set("Uin", "Re*mu0/(rho0*D)");
    model.param().descr("Uin", "\u5e73\u5747\u5165\u53e3\u901f\u5ea6");
    model.param().set("meshsz", "0.005*L");
    model.param().descr("meshsz", "\u7f51\u683c\u5927\u5c0f");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "H"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"D/2", "D/2"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-D/2", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"D/2", "D/2"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"L", "0"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "1e3*eps");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u5de6");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "-D/2+1e3*eps");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u53f3");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmin", "L+D/2-1e3*eps");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").runPre("fin");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("pg1", "def", "\u7528\u6237\u5b9a\u4e49\u5c5e\u6027\u7ec4");
    model.material("mat1").propertyGroup("def").set("density", new String[]{"rho0"});
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu0"});

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("matlnk1", "Link");

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_boxsel2");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "Uin");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_boxsel3");
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().named("geom1_boxsel1");
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("spf2").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf2").feature("inl1").selection().named("geom1_boxsel3");
    model.component("comp1").physics("spf2").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf2").feature("inl1").set("Uavfdf", "Uin");
    model.component("comp1").physics("spf2").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf2").feature("out1").selection().named("geom1_boxsel2");
    model.component("comp1").physics("spf2").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf2").feature("wallbc2").selection().named("geom1_boxsel1");
    model.component("comp1").physics("spf2").feature("wallbc2").set("BoundaryCondition", "Slip");

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "meshsz");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6 (spf2)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf2.U");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u538b\u529b (spf2)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("expr", "p2");
    model.result("pg4").feature("con1").set("number", 40);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("smooth", "internal");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("mir1").setIndex("genpoints", 0, 1, 1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().named("geom1_boxsel2");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop2").selection().named("geom1_boxsel3");
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(2);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("dP_forward", "aveop1(p)-aveop2(p)");
    model.component("comp1").variable("var1").descr("dP_forward", "\u538b\u5dee\uff0c\u6b63\u5411");
    model.component("comp1").variable("var1").set("dP_backward", "aveop2(p2)-aveop1(p2)");
    model.component("comp1").variable("var1").descr("dP_backward", "\u538b\u5dee\uff0c\u53cd\u5411");
    model.component("comp1").variable("var1").set("Di", "dP_backward/dP_forward");
    model.component("comp1").variable("var1").descr("Di", "\u538b\u5dee\u6bd4");

    model.sol("sol1").updateSolution();

    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u538b\u5dee\u6bd4");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"Di"});
    model.result().evaluationGroup("eg1").feature("gev1").set("descr", new String[]{"\u538b\u5dee\u6bd4"});
    model.result().evaluationGroup("eg1").run();

    model.component("comp1").common().create("dtopo1", "DensityTopology");
    model.component("comp1").common("dtopo1").selection().all();
    model.component("comp1").common("dtopo1").selection().set(2);
    model.component("comp1").common("dtopo1").set("filterLengthType", "Custom");
    model.component("comp1").common("dtopo1").set("L_min", "1.5*meshsz");
    model.component("comp1").common("dtopo1").set("interpolationType", "Darcy");
    model.component("comp1").common("dtopo1").set("projectionType", "TanhProjection");
    model.component("comp1").common("dtopo1").set("q_Darcy", "1");
    model.component("comp1").common("dtopo1").set("theta_0", "1");
    model.component("comp1").common("dtopo1").set("discretization", "constant");

    model.component("comp1").variable("var1").set("phi_forward", "spf.Qvd+alpha*(u^2+v^2)");
    model.component("comp1").variable("var1")
         .descr("phi_forward", "\u8017\u6563\u80fd\u5bc6\u5ea6\uff0c\u6b63\u5411\u6d41\u52a8");
    model.component("comp1").variable("var1").set("phi_backward", "spf2.Qvd+alpha*(u2^2+v2^2)");
    model.component("comp1").variable("var1")
         .descr("phi_backward", "\u8017\u6563\u80fd\u5bc6\u5ea6\uff0c\u53cd\u5411\u6d41\u52a8");
    model.component("comp1").variable("var1").set("phi_total", "phi_backward+phi_forward");
    model.component("comp1").variable("var1").descr("phi_total", "\u603b\u8017\u6563");
    model.component("comp1").variable("var1").set("E_forward", "intop1(phi_forward)");
    model.component("comp1").variable("var1").descr("E_forward", "\u80fd\u8017\uff0c\u6b63\u5411\u6d41\u52a8");
    model.component("comp1").variable("var1").set("E_backward", "intop1(phi_backward)");
    model.component("comp1").variable("var1").descr("E_backward", "\u80fd\u8017\uff0c\u53cd\u5411\u6d41\u52a8");
    model.component("comp1").variable("var1").set("obj", "E_backward/E_forward");
    model.component("comp1").variable("var1").descr("obj", "\u76ee\u6807\u51fd\u6570");
    model.component("comp1").variable("var1").set("alpha", "16.*mu0*dtopo1.theta_p/meshsz^2");
    model.component("comp1").variable("var1").descr("alpha", "\u6469\u64e6\u529b");

    model.component("comp1").physics("spf").create("vf1", "VolumeForce", 2);
    model.component("comp1").physics("spf").feature("vf1").selection().set(2);
    model.component("comp1").physics("spf").feature("vf1").set("F", new String[]{"-alpha*u", "-alpha*v", "0"});
    model.component("comp1").physics("spf2").create("vf1", "VolumeForce", 2);
    model.component("comp1").physics("spf2").feature("vf1").selection().set(2);
    model.component("comp1").physics("spf2").feature("vf1").set("F", new String[]{"-alpha*u2", "-alpha*v2", "0"});

    model.study("std1").label("\u4f18\u5316");
    model.study("std1").create("topo", "TopologyOptimization");
    model.study("std1").feature("topo").set("mmamaxiter", 200);
    model.study("std1").feature("topo").set("movelimitactive", true);
    model.study("std1").feature("topo").set("movelimit", 0.2);
    model.study("std1").feature("topo").set("optobj", new String[]{"comp1.obj"});
    model.study("std1").feature("topo").set("descr", new String[]{"\u76ee\u6807\u51fd\u6570"});
    model.study("std1").feature("topo").set("objectivetype", "maximization");
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result("pg1").run();

    model.sol("sol1").feature("o1").set("gcmma", false);

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u62d3\u6251");
    model.result("pg5").set("data", "mir1");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "dtopo1.theta");
    model.result("pg5").feature("surf1").set("colortable", "GrayScale");
    model.result("pg5").feature("surf1").set("colorlegend", false);
    model.result("pg5").feature("surf1").set("rangecoloractive", true);
    model.result("pg5").feature("surf1").set("rangecolormax", 1);
    model.result("pg5").run();

    model.study("std1").feature("topo").set("plot", true);
    model.study("std1").feature("topo").set("plotgroup", "pg5");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.study("std1").feature("topo").set("probewindow", "");

    model.result("pg1").set("data", "mir1");
    model.result("pg1").run();
    model.result("pg1").label("\u6b63\u5411\u6d41\u52a8");
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("xnumber", 30);
    model.result("pg1").feature("arws1").set("ynumber", 30);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "dtopo1.theta");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "white");
    model.result("pg1").feature("surf2").set("rangedataactive", true);
    model.result("pg1").feature("surf2").set("rangedatamax", 0.5);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").label("\u53cd\u5411\u6d41\u52a8");
    model.result("pg3").feature().copy("arws1", "pg1/arws1");
    model.result("pg3").feature().copy("surf2", "pg1/surf2");
    model.result("pg3").run();
    model.result("pg3").feature("arws1").set("expr", new String[]{"u2", "v2"});
    model.result("pg3").feature("arws1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("data", "mir1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").label("\u4f18\u5316\u540e\u7684\u8bbe\u8ba1");

    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("\u56fa\u4f53\u533a\u57df\u7684\u8017\u6563");
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("expr", "intop1(phi_total*(1-dtopo1.theta))/intop1(phi_total)", 0);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("descr", "\u56fa\u4f53\u4e2d\u7684\u76f8\u5bf9\u8017\u6563", 0);
    model.result().evaluationGroup("eg2").run();
    model.result().dataset().create("filt1", "Filter");
    model.result().dataset("filt1").set("expr", "dtopo1.theta");
    model.result().dataset("filt1").set("lowerexpr", "0.5");
    model.result().dataset("filt1").set("useder", false);
    model.result().dataset("filt1").createMesh("comp2", "geom2", "mesh2", "imp1");

    model.component("comp2").mesh("mesh2").feature("imp1").importData();
    model.component("comp2").mesh("mesh2").create("ada1", "Adapt");
    model.component("comp2").mesh("mesh2").feature("ada1").set("method", "modify");
    model.component("comp2").mesh("mesh2").feature("ada1").set("solution", "none");
    model.component("comp2").mesh("mesh2").feature("ada1").set("exprtype", "size");
    model.component("comp2").mesh("mesh2").feature("ada1").set("sizeexpr", "meshsz");
    model.component("comp2").mesh("mesh2").feature("ada1").set("maxrefinement", 0);
    model.component("comp2").mesh("mesh2").feature("ada1").set("maxcoarsening", Double.POSITIVE_INFINITY);
    model.component("comp2").mesh("mesh2").run("ada1");
    model.component("comp2").mesh("mesh2").run();

    model.component("comp2").material().create("matlnk2", "Link");

    model.component("comp2").physics().copy("spf3", "spf");
    model.component("comp2").physics().copy("spf4", "spf2");
    model.component("comp2").physics("spf3").feature("inl1").selection().named("mesh2_imp1_geom1_boxsel2");
    model.component("comp2").physics("spf3").feature("out1").selection().named("mesh2_imp1_geom1_boxsel3");
    model.component("comp2").physics("spf3").feature("wallbc2").selection().named("mesh2_imp1_geom1_boxsel1");
    model.component("comp2").physics("spf3").feature().remove("vf1");
    model.component("comp2").physics("spf4").feature("inl1").selection().named("mesh2_imp1_geom1_boxsel3");
    model.component("comp2").physics("spf4").feature("out1").selection().named("mesh2_imp1_geom1_boxsel2");
    model.component("comp2").physics("spf4").feature("wallbc2").selection().named("mesh2_imp1_geom1_boxsel1");
    model.component("comp2").physics("spf4").feature().remove("vf1");

    model.component("comp2").cpl().create("aveop3", "Average");
    model.component("comp2").cpl("aveop3").set("axisym", true);
    model.component("comp2").cpl("aveop3").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop3").selection().named("mesh2_imp1_geom1_boxsel2");
    model.component("comp2").cpl().duplicate("aveop4", "aveop3");
    model.component("comp2").cpl("aveop4").selection().named("mesh2_imp1_geom1_boxsel3");

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2")
         .set("Di", "(aveop4(p2)-aveop3(p2))/(aveop3(p)-aveop4(p))", "\u538b\u5dee\u6bd4");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std2").feature("stat").setSolveFor("/physics/spf2", false);
    model.study("std2").feature("stat").setSolveFor("/physics/spf3", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf4", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf3", false);
    model.study("std1").feature("stat").setSolveFor("/physics/spf4", false);
    model.study("std2").feature("stat").setSolveFor("/common/dtopo1", false);
    model.study("std2").label("\u9a8c\u8bc1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset3").set("geom", "geom2");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u901f\u5ea6 (spf3)");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u538b\u529b (spf3)");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("con1", "Contour");
    model.result("pg7").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg7").feature("con1").set("showsolutionparams", "on");
    model.result("pg7").feature("con1").set("expr", "p");
    model.result("pg7").feature("con1").set("number", 40);
    model.result("pg7").feature("con1").set("levelrounding", false);
    model.result("pg7").feature("con1").set("smooth", "internal");
    model.result("pg7").feature("con1").set("showsolutionparams", "on");
    model.result("pg7").feature("con1").set("data", "parent");
    model.result().dataset("dset3").set("geom", "geom2");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u901f\u5ea6 (spf4)");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("\u8868\u9762");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("expr", "spf4.U");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("\u538b\u529b (spf4)");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").feature().create("con1", "Contour");
    model.result("pg9").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg9").feature("con1").set("showsolutionparams", "on");
    model.result("pg9").feature("con1").set("expr", "p2");
    model.result("pg9").feature("con1").set("number", 40);
    model.result("pg9").feature("con1").set("levelrounding", false);
    model.result("pg9").feature("con1").set("smooth", "internal");
    model.result("pg9").feature("con1").set("showsolutionparams", "on");
    model.result("pg9").feature("con1").set("data", "parent");

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").label("\u62d3\u6251\u4f18\u5316");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").placeAfter("plotgroup", "pg9");
    model.nodeGroup().move("grp2", 2);

    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u9608\u503c");

    model.nodeGroup("grp2").add("plotgroup", "pg10");
    model.nodeGroup("grp2").add("plotgroup", "pg11");

    model.result().dataset().create("filt2", "Filter");
    model.result().dataset("filt2").label("\u8fc7\u6ee4\u5668");
    model.result().dataset("filt2").set("data", "dset2");
    model.result().dataset("filt2").set("expr", "dtopo1.theta");
    model.result().dataset("filt2").set("lowerexpr", "0.5");
    model.result().dataset("filt2").set("smooth", "none");
    model.result().dataset("filt2").set("useder", false);
    model.result("pg10").set("data", "dset2");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "dtopo1.theta");
    model.result("pg10").feature("surf1").set("rangecoloractive", true);
    model.result("pg10").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg10").feature("surf1").set("rangecolormin", 0);
    model.result("pg10").feature("surf1").set("rangecolormax", 1);
    model.result("pg11").set("data", "filt2");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "1");
    model.result("pg11").feature("surf1").set("coloring", "uniform");
    model.result("pg11").feature("surf1").set("color", "gray");
    model.result("pg11").feature("surf1").set("titletype", "none");
    model.result("pg6").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup().move("grp3", 1);
    model.nodeGroup("grp3").add("plotgroup", "pg6");
    model.nodeGroup("grp3").add("plotgroup", "pg7");
    model.nodeGroup("grp3").add("plotgroup", "pg8");
    model.nodeGroup("grp3").add("plotgroup", "pg9");
    model.nodeGroup("grp3").label("\u9a8c\u8bc1");
    model.nodeGroup().remove("grp2");

    model.result().evaluationGroup("eg1").create("gev2", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev2").set("data", "dset3");
    model.result().evaluationGroup("eg1").feature("gev2").set("expr", new String[]{"Di"});
    model.result().evaluationGroup("eg1").feature("gev2").set("descr", new String[]{"\u538b\u5dee\u6bd4"});
    model.result().evaluationGroup("eg1").run();

    model.title("Tesla \u5fae\u9600\u4f18\u5316");

    model
         .description("\u672c\u4f8b\u5bf9\u4e00\u4e2a Tesla \u5fae\u9600\u8fdb\u884c\u62d3\u6251\u4f18\u5316\u3002\u8be5 Tesla \u5fae\u9600\u901a\u8fc7\u6469\u64e6\u529b\u6765\u6291\u5236\u9006\u6d41\uff0c\u800c\u4e0d\u662f\u901a\u8fc7\u5e38\u89c1\u7684\u6d3b\u52a8\u90e8\u4ef6\u6765\u63a7\u5236\u3002\u8fd9\u79cd\u8bbe\u8ba1\u53ef\u4ee5\u901a\u8fc7\u5efa\u6a21\u57df\u5185\u7279\u5b9a\u6750\u6599\u91cf\u7684\u5206\u5e03\u6765\u4f18\u5316\u3002\u76ee\u6807\u662f\u5f97\u5230\u5411\u524d/\u5411\u540e\u6d41\u7ecf\u88c5\u7f6e\u7684\u6700\u5927\u538b\u964d\u6bd4\u3002");

    model.component("comp1").mesh("mesh1").clearMesh();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("tesla_microvalve_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
