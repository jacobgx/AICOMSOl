/*
 * supersonic_ejector.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class supersonic_ejector {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\High_Mach_Number_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("hmnf", "HighMachNumberFlowTurbulentkeps", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/hmnf", true);

    model.component("comp1").geom("geom1").insertFile("supersonic_ejector_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("sel4");

    model.param().set("P1", "5[atm]");
    model.param().descr("P1", "\u4e3b\u6d41\u603b\u538b");
    model.param().set("P2", "0.55[atm]");
    model.param().descr("P2", "\u4e8c\u6b21\u6d41\u603b\u538b");
    model.param().set("Pout", "1[atm]");
    model.param().descr("Pout", "\u51fa\u53e3\u538b\u529b");
    model.param().set("T1", "300[K]");
    model.param().descr("T1", "\u4e3b\u6d41\u603b\u6e29\u5ea6");
    model.param().set("T2", "T1");
    model.param().descr("T2", "\u4e8c\u6b21\u6d41\u603b\u6e29\u5ea6");
    model.param().set("Rs", "287[J/kg/K]");
    model.param().descr("Rs", "\u6bd4\u6c14\u4f53\u5e38\u6570");
    model.param().set("gamma", "1.41");
    model.param().descr("gamma", "\u6bd4\u70ed\u7387");
    model.param().set("iso_diff", "0");
    model.param().descr("iso_diff", "\u5404\u5411\u540c\u6027\u6269\u6563\u7cfb\u6570");

    model.component("comp1").physics("hmnf").prop("InconsistentStabilization").set("HeatIsotropicDiffusion", true);
    model.component("comp1").physics("hmnf").prop("InconsistentStabilization").set("delidht", "iso_diff");
    model.component("comp1").physics("hmnf").prop("InconsistentStabilization").set("IsotropicDiffusion", true);
    model.component("comp1").physics("hmnf").prop("InconsistentStabilization").set("delid", "iso_diff");
    model.component("comp1").physics("hmnf").feature("fluid1").set("Rs_mat", "userdef");
    model.component("comp1").physics("hmnf").feature("fluid1").set("Rs", "Rs");
    model.component("comp1").physics("hmnf").feature("fluid1").set("CpOrGammaOption", "gamma");
    model.component("comp1").physics("hmnf").feature("fluid1").set("gamma_mat", "userdef");
    model.component("comp1").physics("hmnf").feature("fluid1").set("gamma", "gamma");
    model.component("comp1").physics("hmnf").feature("init1").set("u_init", new int[]{0, 0, 100});
    model.component("comp1").physics("hmnf").feature("init1").set("p_init", "2[atm]");
    model.component("comp1").physics("hmnf").create("hminl1", "HighMachNumberFlowInlet", 1);
    model.component("comp1").physics("hmnf").feature("hminl1").selection().named("geom1_sel2");
    model.component("comp1").physics("hmnf").feature("hminl1").set("InputState", "TotalConditions");
    model.component("comp1").physics("hmnf").feature("hminl1").set("p0tot", "P1");
    model.component("comp1").physics("hmnf").feature("hminl1").set("T0tot", "T1");
    model.component("comp1").physics("hmnf").feature("hminl1").set("Ma0", 0.14);
    model.component("comp1").physics("hmnf").create("hminl2", "HighMachNumberFlowInlet", 1);
    model.component("comp1").physics("hmnf").feature("hminl2").selection().named("geom1_sel3");
    model.component("comp1").physics("hmnf").feature("hminl2").set("InputState", "TotalConditions");
    model.component("comp1").physics("hmnf").feature("hminl2").set("p0tot", "P2");
    model.component("comp1").physics("hmnf").feature("hminl2").set("T0tot", "T2");
    model.component("comp1").physics("hmnf").feature("hminl2").set("Ma0", 0.01);
    model.component("comp1").physics("hmnf").create("hmout1", "HighMachNumberFlowOutlet", 1);
    model.component("comp1").physics("hmnf").feature("hmout1").selection().named("geom1_sel4");
    model.component("comp1").physics("hmnf").feature("hmout1").set("FlowCondition", "Subsonic");
    model.component("comp1").physics("hmnf").feature("hmout1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("hmnf").feature("hmout1").set("p0", "Pout");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh2").feature("size1").selection().set(4, 5);
    model.component("comp1").mesh("mesh2").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh2").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh2").create("size2", "Size");
    model.component("comp1").mesh("mesh2").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh2").feature("size2").selection().named("geom1_sel1");
    model.component("comp1").mesh("mesh2").feature("size2").selection().set(6, 7, 8, 9, 11, 12, 13, 14, 15);
    model.component("comp1").mesh("mesh2").feature("size2").set("table", "cfd");
    model.component("comp1").mesh("mesh2").feature("size2").set("hauto", 4);
    model.component("comp1").mesh("mesh2").create("size3", "Size");
    model.component("comp1").mesh("mesh2").feature("size3").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("size3").selection().set(2);
    model.component("comp1").mesh("mesh2").feature("size3").set("table", "cfd");
    model.component("comp1").mesh("mesh2").feature("size3").set("hauto", 1);
    model.component("comp1").mesh("mesh2").create("size4", "Size");
    model.component("comp1").mesh("mesh2").feature("size4").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("size4").selection().set(3);
    model.component("comp1").mesh("mesh2").feature("size4").set("table", "cfd");
    model.component("comp1").mesh("mesh2").feature("size4").set("hauto", 7);
    model.component("comp1").mesh("mesh2").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh2").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh2").create("cr1", "CornerRefinement");
    model.component("comp1").mesh("mesh2").feature("cr1").selection("boundary").named("geom1_sel1");
    model.component("comp1").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh2").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh2").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh2").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh2").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh2").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").selection().named("geom1_sel1");
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").selection()
         .set(6, 7, 9, 11, 12, 13, 14, 15);
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").set("blnlayers", 5);
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").set("blhmin", "5e-5");
    model.component("comp1").mesh("mesh2").feature("bl1").create("blp1", "BndLayerProp");
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp1").set("blnlayers", 10);
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp1").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp1").set("blhmin", "1e-5");
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp1").selection().set(4, 5, 8);
    model.component("comp1").mesh("mesh2").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "d_throat", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "d_throat", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "P2", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "Pout 0.55[atm]", 0);
    model.study("std1").feature("stat").setIndex("pname", "d_throat", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "m", 1);
    model.study("std1").feature("stat").setIndex("pname", "d_throat", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "m", 1);
    model.study("std1").feature("stat").setIndex("pname", "iso_diff", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "0.5 0", 1);
    model.study("std1").feature("stat").set("pcontinuationmode", "no");
    model.study("std1").feature("stat").set("preusesol", "yes");
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").set("errestandadap", "adaption");
    model.study("std1").feature("stat2").set("meshadaptmethod", "modify");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s2").feature("se1").feature("ss1").set("linsolver", "i1");
    model.sol("sol1").feature("s2").feature("adDef").set("allowcoarsening", false);
    model.sol("sol1").feature("s2").feature("se1").feature("ss2").set("linsolver", "i2");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset3").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (hmnf)");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (hmnf)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").set("dataisaxisym", "off");
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
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset3");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4e09\u7ef4\u901f\u5ea6 (hmnf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").label("\u5916\u58c1");
    model.result().dataset("edg1").set("data", "dset3");
    model.result().dataset("edg1").selection().geom("geom1", 1);
    model.result().dataset("edg1").selection().set(4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u58c1\u5206\u8fa8\u7387 (hmnf)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("line1", "Line");
    model.result("pg4").feature("line1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("expr", "hmnf.Delta_wPlus");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("data", "parent");
    model.result("pg4").feature("line1").feature().create("hght1", "Height");
    model.result("pg4").feature("line1").feature("hght1").label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f");
    model.result("pg4").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg4").feature("line1").feature("hght1").set("expr", "hmnf.WRHeightExpr");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u6e29\u5ea6 (hmnf)");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 3, 0);
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "T");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u9a6c\u8d6b\u6570 (hmnf)");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 3, 0);
    model.result("pg6").set("dataisaxisym", "off");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "hmnf.Ma");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u8d28\u91cf\u6d41\u8ba1\u7b97\u7ec4");
    model.result().evaluationGroup("eg1").create("int1", "IntLine");
    model.result().evaluationGroup("eg1").feature("int1").set("intsurface", true);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("expr", "hmnf.rho*w", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("int2", "int1");
    model.result().evaluationGroup("eg1").feature().duplicate("int3", "int2");
    model.result().evaluationGroup("eg1").feature("int1").label("\u4e3b\u8d28\u91cf\u6d41");
    model.result().evaluationGroup("eg1").feature("int1").selection().set(2);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "\u4e3b\u8d28\u91cf\u6d41", 0);
    model.result().evaluationGroup("eg1").feature("int2").label("\u7b2c\u4e8c\u8d28\u91cf\u6d41");
    model.result().evaluationGroup("eg1").feature("int2").selection().set(10);
    model.result().evaluationGroup("eg1").feature("int2").setIndex("descr", "\u7b2c\u4e8c\u8d28\u91cf\u6d41", 0);
    model.result().evaluationGroup("eg1").feature("int3").label("\u6df7\u5408\u8d28\u91cf\u6d41");
    model.result().evaluationGroup("eg1").feature("int3").selection().set(3);
    model.result().evaluationGroup("eg1").feature("int3").setIndex("descr", "\u6df7\u5408\u8d28\u91cf\u6d41", 0);
    model.result().evaluationGroup("eg1").set("type", "general");
    model.result().evaluationGroup("eg1").set("generalexpr", "int1+int2-int3");
    model.result().evaluationGroup("eg1").set("keepchildnodes", true);
    model.result().evaluationGroup("eg1")
         .set("generalheader", "Inflow \u548c Outflow \u4e4b\u95f4\u7684\u5dee\u503c");
    model.result().evaluationGroup("eg1").run();
    model.result().numerical().create("av1", "AvLine");
    model.result().numerical("av1").set("intsurface", true);
    model.result().numerical("av1").label("\u4e3b\u5165\u53e3\u7684\u9a6c\u8d6b\u6570");
    model.result().numerical("av1").set("data", "dset3");
    model.result().numerical("av1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("av1").selection().named("geom1_sel2");
    model.result().numerical("av1").setIndex("expr", "hmnf.Ma", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u4e3b\u5165\u53e3\u7684\u9a6c\u8d6b\u6570");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();
    model.result().numerical().create("av2", "AvLine");
    model.result().numerical("av2").set("intsurface", true);
    model.result().numerical("av2").label("\u6b21\u5165\u53e3\u7684\u9a6c\u8d6b\u6570");
    model.result().numerical("av2").set("data", "dset3");
    model.result().numerical("av2").setIndex("looplevelinput", "last", 0);
    model.result().numerical("av2").selection().named("geom1_sel3");
    model.result().numerical("av2").setIndex("expr", "hmnf.Ma", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u6b21\u5165\u53e3\u7684\u9a6c\u8d6b\u6570");
    model.result().numerical("av2").set("table", "tbl2");
    model.result().numerical("av2").setResult();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").set("data", "dset3");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").label("\u6e4d\u52a8\u80fd");
    model.result("pg7").set("data", "mir1");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "k");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u6e29\u5ea6\uff0c\u4e8c\u7ef4");
    model.result("pg8").set("data", "mir1");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "T");
    model.result("pg8").feature("surf1").set("colortable", "Thermal");
    model.result("pg8").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").label("\u4e2d\u5fc3\u7ebf");
    model.result().dataset("cln1").set("data", "dset3");
    model.result().dataset("cln1").setIndex("genpoints", 0, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "L_mixing", 1, 1);
    model.result().dataset().create("cln2", "CutLine2D");
    model.result().dataset("cln2").label("\u58c1");
    model.result().dataset("cln2").set("data", "dset3");
    model.result().dataset("cln2").setIndex("genpoints", "d_mixing/2", 0, 0);
    model.result().dataset("cln2").setIndex("genpoints", "d_mixing/2", 1, 0);
    model.result().dataset("cln2").setIndex("genpoints", "L_mixing", 1, 1);
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").set("legendpos", "lowerright");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg9").feature("lngr1").set("linewidth", "preference");
    model.result("pg9").feature("lngr1").set("data", "cln1");
    model.result("pg9").feature("lngr1").setIndex("looplevelinput", "last", 0);
    model.result("pg9").feature("lngr1").set("expr", "p");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "z");
    model.result("pg9").feature("lngr1").set("legend", true);
    model.result("pg9").feature("lngr1").set("legendmethod", "manual");
    model.result("pg9").feature("lngr1").setIndex("legends", "\u4e2d\u5fc3\u7ebf", 0);
    model.result("pg9").run();
    model.result("pg9").create("lngr2", "LineGraph");
    model.result("pg9").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg9").feature("lngr2").set("linewidth", "preference");
    model.result("pg9").feature("lngr2").set("data", "cln2");
    model.result("pg9").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg9").feature("lngr2").set("expr", "p");
    model.result("pg9").feature("lngr2").set("xdata", "expr");
    model.result("pg9").feature("lngr2").set("xdataexpr", "z");
    model.result("pg9").feature("lngr2").set("legend", true);
    model.result("pg9").feature("lngr2").set("legendmethod", "manual");
    model.result("pg9").feature("lngr2").setIndex("legends", "\u58c1", 0);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");
    model.result("pg1").run();
    model.result("pg6").run();
    model.result("pg6").set("data", "mir1");
    model.result("pg6").run();
    model.result("pg1").run();
    model.result("pg1").set("view", "view2");
    model.result("pg1").run();

    model.title("\u8d85\u97f3\u901f\u7a7a\u6c14\u55b7\u5c04\u5668");

    model
         .description("\u672c\u7814\u7a76\u4f7f\u7528\u201cCFD \u6a21\u5757\u201d\u7684\u201c\u9ad8\u9a6c\u8d6b\u6570\u6d41\u52a8\u201d\u63a5\u53e3\u6a21\u62df\u901a\u8fc7\u8d85\u97f3\u901f\u55b7\u5c04\u5668\u7684\u53ef\u538b\u7f29\u6e4d\u6d41\u3002\n\n\u55b7\u5c04\u5668\u662f\u4e00\u79cd\u7ed3\u6784\u7b80\u5355\u7684\u673a\u68b0\u90e8\u4ef6\uff0c\u5728\u591a\u4e2a\u9886\u57df\u6709\u7740\u5e7f\u6cdb\u7684\u5e94\u7528\uff0c\u5305\u62ec\u5de5\u4e1a\u5236\u51b7\u3001\u771f\u7a7a\u4ea7\u751f\u3001\u6c14\u4f53\u518d\u5faa\u73af\u4ee5\u53ca\u98de\u673a\u63a8\u8fdb\u7cfb\u7edf\u4e2d\u7684\u63a8\u529b\u589e\u5f3a\u7b49\u3002\n\n\u55b7\u5c04\u5668\u901a\u8fc7\u9ad8\u901f\u4e3b\u5c04\u6d41\u7684\u52a8\u91cf\u548c\u80fd\u91cf\u4f20\u9012\u6765\u8bf1\u5bfc\u4ea7\u751f\u4e8c\u6b21\u6d41\u3002\u9ad8\u80fd\u6d41\u4f53\uff08\u4e3b\u6d41\uff09\u6d41\u7ecf\u6536\u655b-\u53d1\u6563\u55b7\u5634\uff0c\u8fbe\u5230\u8d85\u97f3\u901f\u72b6\u6001\u3002\u79bb\u5f00\u55b7\u5634\u540e\uff0c\u4e3b\u6d41\u4e0e\u4e8c\u6b21\u6d41\u76f8\u4e92\u4f5c\u7528\uff0c\u5e76\u901a\u8fc7\u5377\u5438\u6548\u5e94\u52a0\u901f\u3002\u8fd9\u4e24\u79cd\u6d41\u4f53\u7684\u6df7\u5408\u53d1\u751f\u5728\u4e00\u4e2a\u79f0\u4e3a\u6df7\u5408\u5ba4\u7684\u7b49\u622a\u9762\u7ba1\u9053\u4e2d\uff0c\u6b64\u5904\u53ef\u4ee5\u89c2\u5bdf\u5230\u6df7\u5408\u5c42\u4e0e\u6fc0\u6ce2\u4e4b\u95f4\u7684\u590d\u6742\u76f8\u4e92\u4f5c\u7528\u3002\u4e3a\u4e86\u6062\u590d\u538b\u529b\u5e76\u4f7f\u6d41\u52a8\u56de\u5230\u505c\u6ede\u72b6\u6001\uff0c\u901a\u5e38\u4f1a\u5728\u51fa\u53e3\u524d\u653e\u7f6e\u4e00\u4e2a\u6269\u538b\u5668\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("supersonic_ejector.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
