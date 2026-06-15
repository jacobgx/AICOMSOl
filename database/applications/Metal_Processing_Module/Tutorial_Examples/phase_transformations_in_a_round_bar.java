/*
 * phase_transformations_in_a_round_bar.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:07 by COMSOL 6.3.0.290. */
public class phase_transformations_in_a_round_bar {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Metal_Processing_Module\\Tutorial_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("metp", "MetalPhaseTransformation", "geom1");
    model.component("comp1").physics("metp").prop("HeatTransfer").set("latentheat", "1");

    model.component("comp1").multiphysics().create("lht1", "PhaseTransformationLatentHeat", 2);
    model.component("comp1").multiphysics("lht1").set("Metphase_physics", "metp");
    model.component("comp1").multiphysics("lht1").set("HeatTransfer_physics", "ht");
    model.component("comp1").multiphysics("lht1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/metp", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/lht1", true);

    model.param().set("r", "0.04[m]");
    model.param().descr("r", "\u91d1\u5c5e\u6761\u534a\u5f84");
    model.param().set("latheat", "670000[kJ/m^3]");
    model.param().descr("latheat", "\u76f8\u53d8\u6f5c\u70ed");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 15);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "K");
    model.component("comp1").func("int1").setIndex("table", 600, 0, 0);
    model.component("comp1").func("int1").setIndex("table", "0.0001", 0, 1);
    model.component("comp1").func("int1").setIndex("table", 620, 1, 0);
    model.component("comp1").func("int1").setIndex("table", 0.0018, 1, 1);
    model.component("comp1").func("int1").setIndex("table", 800, 2, 0);
    model.component("comp1").func("int1").setIndex("table", 0, 2, 1);
    model.component("comp1").func("int1").setIndex("argunit", "degC", 0);
    model.component("comp1").func("int1").setIndex("fununit", "1/s", 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").set("funcname", "L");
    model.component("comp1").func("int2").setIndex("table", 600, 0, 0);
    model.component("comp1").func("int2").setIndex("table", 0, 0, 1);
    model.component("comp1").func("int2").setIndex("table", 620, 1, 0);
    model.component("comp1").func("int2").setIndex("table", "0.0002", 1, 1);
    model.component("comp1").func("int2").setIndex("table", 800, 2, 0);
    model.component("comp1").func("int2").setIndex("table", 0.002, 2, 1);
    model.component("comp1").func("int2").setIndex("argunit", "degC", 0);
    model.component("comp1").func("int2").setIndex("fununit", "1/s", 0);
    model.component("comp1").func().create("int3", "Interpolation");
    model.component("comp1").func("int3").set("funcname", "F");
    model.component("comp1").func("int3").setIndex("table", 340, 0, 0);
    model.component("comp1").func("int3").setIndex("table", 0, 0, 1);
    model.component("comp1").func("int3").setIndex("table", 350, 1, 0);
    model.component("comp1").func("int3").setIndex("table", 0.014, 1, 1);
    model.component("comp1").func("int3").setIndex("table", 450, 2, 0);
    model.component("comp1").func("int3").setIndex("table", 0.067, 2, 1);
    model.component("comp1").func("int3").setIndex("table", 550, 3, 0);
    model.component("comp1").func("int3").setIndex("table", 0, 3, 1);
    model.component("comp1").func("int3").setIndex("argunit", "degC", 0);
    model.component("comp1").func("int3").setIndex("fununit", "1/s", 0);
    model.component("comp1").func().create("int4", "Interpolation");
    model.component("comp1").func("int4").set("funcname", "G");
    model.component("comp1").func("int4").setIndex("table", 450, 0, 0);
    model.component("comp1").func("int4").setIndex("table", 0, 0, 1);
    model.component("comp1").func("int4").setIndex("table", 550, 1, 0);
    model.component("comp1").func("int4").setIndex("table", 0.067, 1, 1);
    model.component("comp1").func("int4").setIndex("argunit", "degC", 0);
    model.component("comp1").func("int4").setIndex("fununit", "1/s", 0);
    model.component("comp1").func().create("int5", "Interpolation");
    model.component("comp1").func("int5").set("funcname", "H");
    model.component("comp1").func("int5").setIndex("table", -43000, 0, 0);
    model.component("comp1").func("int5").setIndex("table", 0.2, 0, 1);
    model.component("comp1").func("int5").setIndex("table", -15000, 1, 0);
    model.component("comp1").func("int5").setIndex("table", 1, 1, 1);
    model.component("comp1").func("int5").setIndex("table", -7200, 2, 0);
    model.component("comp1").func("int5").setIndex("table", 1.5, 2, 1);
    model.component("comp1").func("int5").setIndex("table", -1500, 3, 0);
    model.component("comp1").func("int5").setIndex("table", 0.22, 3, 1);
    model.component("comp1").func("int5").setIndex("table", -700, 4, 0);
    model.component("comp1").func("int5").setIndex("table", 0.1, 4, 1);
    model.component("comp1").func("int5").setIndex("table", -70, 5, 0);
    model.component("comp1").func("int5").setIndex("table", 0.0044, 5, 1);
    model.component("comp1").func("int5").setIndex("argunit", "K/h", 0);
    model.component("comp1").func("int5").setIndex("fununit", 1, 0);
    model.component("comp1").func().create("int6", "Interpolation");
    model.component("comp1").func("int6").set("funcname", "htc");
    model.component("comp1").func("int6").setIndex("table", 300, 0, 0);
    model.component("comp1").func("int6").setIndex("table", 200, 0, 1);
    model.component("comp1").func("int6").setIndex("table", 500, 1, 0);
    model.component("comp1").func("int6").setIndex("table", 3000, 1, 1);
    model.component("comp1").func("int6").setIndex("table", 650, 2, 0);
    model.component("comp1").func("int6").setIndex("table", 700, 2, 1);
    model.component("comp1").func("int6").set("interp", "piecewisecubic");
    model.component("comp1").func("int6").setIndex("argunit", "degC", 0);
    model.component("comp1").func("int6").setIndex("fununit", "W/(m^2*K)", 0);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u5965\u6c0f\u4f53");
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"25"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7900"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"550"});
    model.component("comp1").material().duplicate("mat2", "mat1");
    model.component("comp1").material("mat2").label("\u94c1\u7d20\u4f53\u548c\u73e0\u5149\u4f53");
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"45"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"7800"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"700"});
    model.component("comp1").material().duplicate("mat3", "mat2");
    model.component("comp1").material("mat3").label("\u8d1d\u6c0f\u4f53");
    model.component("comp1").material().duplicate("mat4", "mat3");
    model.component("comp1").material("mat4").label("\u9a6c\u6c0f\u4f53");

    model.component("comp1").physics("metp").prop("MaterialProperties").runCommand("makecompoundmaterial");
    model.component("comp1").physics("metp").feature("phase1").label("\u5965\u6c0f\u4f53");
    model.component("comp1").physics("metp").feature("phase1").set("phaseMaterial", "mat1");
    model.component("comp1").physics("metp").feature("phase2").label("\u94c1\u7d20\u4f53\u548c\u73e0\u5149\u4f53");
    model.component("comp1").physics("metp").feature("phase2").set("phaseMaterial", "mat2");
    model.component("comp1").physics("metp").create("phase3", "MetallurgicalPhase", 2);
    model.component("comp1").physics("metp").feature("phase3").label("\u8d1d\u6c0f\u4f53");
    model.component("comp1").physics("metp").feature("phase3").set("phaseMaterial", "mat3");
    model.component("comp1").physics("metp").create("phase4", "MetallurgicalPhase", 2);
    model.component("comp1").physics("metp").feature("phase4").label("\u9a6c\u6c0f\u4f53");
    model.component("comp1").physics("metp").feature("phase4").set("phaseMaterial", "mat4");
    model.component("comp1").physics("metp").feature("ptran1")
         .label("\u5965\u6c0f\u4f53\u5230\u94c1\u7d20\u4f53\u548c\u73e0\u5149\u4f53");
    model.component("comp1").physics("metp").feature("ptran1").set("K", "K(metp.T)");
    model.component("comp1").physics("metp").feature("ptran1").set("L", "L(metp.T)");
    model.component("comp1").physics("metp").feature("ptran1").set("dH", "latheat");
    model.component("comp1").physics("metp").create("ptran2", "PhaseTransformation", 2);
    model.component("comp1").physics("metp").feature("ptran2").label("\u5965\u6c0f\u4f53\u5230\u8d1d\u6c0f\u4f53");
    model.component("comp1").physics("metp").feature("ptran2").set("phasei", "phase1");
    model.component("comp1").physics("metp").feature("ptran2").set("phasej", "phase3");
    model.component("comp1").physics("metp").feature("ptran2").set("K", "F(metp.T)*H(metp.Tt)");
    model.component("comp1").physics("metp").feature("ptran2").set("L", "G(metp.T)*H(metp.Tt)");
    model.component("comp1").physics("metp").feature("ptran2").set("dH", "latheat");
    model.component("comp1").physics("metp").create("ptran3", "PhaseTransformation", 2);
    model.component("comp1").physics("metp").feature("ptran3").label("\u5965\u6c0f\u4f53\u5230\u9a6c\u6c0f\u4f53");
    model.component("comp1").physics("metp").feature("ptran3").set("phasei", "phase1");
    model.component("comp1").physics("metp").feature("ptran3").set("phasej", "phase4");
    model.component("comp1").physics("metp").feature("ptran3").set("ptModel", "KoistinenMarburger");
    model.component("comp1").physics("metp").feature("ptran3").set("Ms", "370[degC]");
    model.component("comp1").physics("metp").feature("ptran3").set("dH", "latheat");

    model.component("comp1").material("mat5").selection().set(1);

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "900[degC]");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("ht").feature("sym1").selection().set(1, 2);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(3);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "htc(T)");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "80[degC]");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);

    model.study("std1").feature("time").set("tlist", "range(0,10,1800)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 181, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"metp.phase1.xi"});
    model.result("pg2").label("\u5965\u6c0f\u4f53 (metp)");
    model.result("pg2").feature("surf1").set("coloring", "gradient");
    model.result("pg2").feature("surf1").set("topcolor", "custom");
    model.result("pg2").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg2").feature("surf1").set("bottomcolor", "custom");
    model.result("pg2").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg2").feature("surf1").set("colortablerev", false);
    model.result("pg2").feature("surf1").set("titletype", "manual");
    model.result("pg2").feature("surf1").set("title", "\u5965\u6c0f\u4f53");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 181, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"metp.phase2.xi"});
    model.result("pg3").label("\u94c1\u7d20\u4f53\u548c\u73e0\u5149\u4f53 (metp)");
    model.result("pg3").feature("surf1").set("coloring", "gradient");
    model.result("pg3").feature("surf1").set("topcolor", "custom");
    model.result("pg3").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg3").feature("surf1").set("bottomcolor", "custom");
    model.result("pg3").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg3").feature("surf1").set("colortablerev", false);
    model.result("pg3").feature("surf1").set("titletype", "manual");
    model.result("pg3").feature("surf1").set("title", "\u94c1\u7d20\u4f53\u548c\u73e0\u5149\u4f53");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 181, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"metp.phase3.xi"});
    model.result("pg4").label("\u8d1d\u6c0f\u4f53 (metp)");
    model.result("pg4").feature("surf1").set("coloring", "gradient");
    model.result("pg4").feature("surf1").set("topcolor", "custom");
    model.result("pg4").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg4").feature("surf1").set("bottomcolor", "custom");
    model.result("pg4").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg4").feature("surf1").set("colortablerev", false);
    model.result("pg4").feature("surf1").set("titletype", "manual");
    model.result("pg4").feature("surf1").set("title", "\u8d1d\u6c0f\u4f53");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 181, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"metp.phase4.xi"});
    model.result("pg5").label("\u9a6c\u6c0f\u4f53 (metp)");
    model.result("pg5").feature("surf1").set("coloring", "gradient");
    model.result("pg5").feature("surf1").set("topcolor", "custom");
    model.result("pg5").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg5").feature("surf1").set("bottomcolor", "custom");
    model.result("pg5").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg5").feature("surf1").set("colortablerev", false);
    model.result("pg5").feature("surf1").set("titletype", "manual");
    model.result("pg5").feature("surf1").set("title", "\u9a6c\u6c0f\u4f53");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("legendpos", "middleright");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("expr", "metp.phase1.xi");
    model.result("pg6").feature("lngr1").set("descr", "\u76f8\u5206\u6570");
    model.result("pg6").feature("lngr1").set("xdata", "reversedarc");
    model.result("pg6").feature("lngr1").set("linewidth", 2);
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "\u5965\u6c0f\u4f53\u6bd4\u4f8b", 0);
    model.result("pg6").feature("lngr1").selection().set(2);
    model.result("pg6").feature().duplicate("lngr2", "lngr1");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("expr", "metp.phase2.xi");
    model.result("pg6").feature("lngr2")
         .setIndex("legends", "\u94c1\u7d20\u4f53\u548c\u73e0\u5149\u4f53\u6bd4\u4f8b", 0);
    model.result("pg6").feature().duplicate("lngr3", "lngr2");
    model.result("pg6").run();
    model.result("pg6").feature("lngr3").set("expr", "metp.phase3.xi");
    model.result("pg6").feature("lngr3").setIndex("legends", "\u8d1d\u6c0f\u4f53\u6bd4\u4f8b", 0);
    model.result("pg6").feature().duplicate("lngr4", "lngr3");
    model.result("pg6").run();
    model.result("pg6").feature("lngr4").setIndex("legends", "\u6bd4\u4f8b", 0);
    model.result("pg6").feature("lngr4").set("expr", "metp.phase4.xi");
    model.result("pg6").feature("lngr4").setIndex("legends", "\u9a6c\u6c0f\u4f53\u6bd4\u4f8b", 0);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("legendpos", "middleright");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").selection().set(1);
    model.result("pg7").feature("ptgr1").set("linewidth", 2);
    model.result("pg7").feature("ptgr1").set("legend", true);
    model.result("pg7").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg7").feature("ptgr1").setIndex("legends", "\u4e2d\u5fc3", 0);
    model.result("pg7").run();
    model.result("pg7").create("ptgr2", "PointGraph");
    model.result("pg7").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr2").set("linewidth", "preference");
    model.result("pg7").feature("ptgr2").selection().set(3);
    model.result("pg7").feature("ptgr2").set("linewidth", 2);
    model.result("pg7").feature("ptgr2").set("legend", true);
    model.result("pg7").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg7").feature("ptgr2").setIndex("legends", "\u8868\u9762", 0);
    model.result("pg2").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg5").run();

    model.title("\u5706\u68d2\u4e2d\u7684\u76f8\u53d8");

    model
         .description("\u94a2\u7b4b\u7684\u4e8c\u7ef4\u6a21\u578b\u7528\u4e8e\u6a21\u62df\u5965\u6c0f\u4f53\u72b6\u6001\u7684\u6cb9\u6dec\u706b\u3002\u5176\u4e2d\u91c7\u7528\u6269\u6563\u578b\u76f8\u53d8\u548c\u4f4d\u79fb\u578b\u76f8\u53d8\u76f8\u7ed3\u5408\u7684\u65b9\u6cd5\uff0c\u8ba1\u7b97\u4e86\u68d2\u7684\u5f84\u5411\u76f8\u7ec4\u6210\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("phase_transformations_in_a_round_bar.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
