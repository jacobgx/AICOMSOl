/*
 * microstrip_line_crosstalk.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:33 by COMSOL 6.3.0.290. */
public class microstrip_line_crosstalk {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\EMI_EMC_Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("temw", "TransientElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/temw", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "600[MHz]", "\u9891\u7387");
    model.param().set("er_sub", "3.38", "\u57fa\u677f\u4ecb\u7535\u5e38\u6570");
    model.param()
         .set("er_eff", "(er_sub+1)/2+(er_sub-1)/2/sqrt(1+12*tsub/lwidth)", "\u57fa\u677f\u6709\u6548\u4ecb\u7535\u5e38\u6570");
    model.param().set("vph", "c_const/sqrt(er_eff)", "\u76f8\u901f\u5ea6");
    model.param().set("Tb", "1/f0/2", "\u4e00\u4e2a\u6bd4\u7279\u5bbd\u5ea6");
    model.param().set("drate", "1/Tb", "\u6570\u636e\u901f\u7387");
    model.param().set("delay", "blength/vph", "\u4f20\u64ad\u65f6\u95f4");
    model.param().set("sim_time_max", "delay+Tb", "\u6700\u5927\u4eff\u771f\u65f6\u95f4");
    model.param().set("pulse_w", "vph*Tb", "\u57fa\u677f\u4e2d\u7684\u8109\u51b2\u5bbd\u5ea6");
    model.param().set("blength", "6[inch]", "\u57fa\u677f\u957f\u5ea6");
    model.param().set("bwidth", "1[inch]", "\u57fa\u677f\u5bbd\u5ea6");
    model.param().set("lwidth", "1.13[mm]", "\u7ebf\u5bbd");
    model.param().set("spacing", "1.8[mm]", "\u7ebf\u95f4\u8ddd");
    model.param().set("tsub", "20[mil]", "\u57fa\u677f\u539a\u5ea6");
    model.param().set("fmax", "5[GHz]", "\u6700\u5927\u8109\u51b2\u9891\u7387\u6210\u5206");
    model.param().set("lmin", "vph/fmax", "\u57fa\u677f\u4e2d\u7684\u6ce2\u957f");
    model.param().set("hm", "lmin/5", "\u6700\u5927\u7f51\u683c\u5927\u5c0f");
    model.param().set("sim_time_step", "0.2 * hm/vph", "\u4eff\u771f\u65f6\u6b65");

    model.component("comp1").geom("geom1").lengthUnit("in");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"blength+0.5", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "bwidth", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "tsub*15", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-0.25", "-bwidth/2", "0"});
    model.component("comp1").geom("geom1").feature("blk1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 0.25, 0);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"blength", "bwidth", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "tsub", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "-bwidth/2", "0"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"blength", "lwidth", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "tsub", 2);
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"0", "-spacing/2-lwidth", "0"});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("blk3");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").runPre("fin");

    model.func().create("rect1", "Rectangle");
    model.func("rect1").set("lower", 0);
    model.func("rect1").set("upper", "Tb-Tb/4");
    model.func("rect1").set("smooth", "Tb/4");
    model.func().create("an1", "Analytic");
    model.func("an1").set("expr", "rect1((t-Tb/8)/1[s])");
    model.func("an1").set("args", "t");
    model.func("an1").setIndex("argunit", "s", 0);
    model.func("an1").set("fununit", "V");
    model.func("an1").setIndex("plotargs", "2*Tb", 0, 2);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(2, 4, 5, 6, 7);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"er_sub"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").physics("temw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("temw").feature("pec2").selection().set(16, 24);
    model.component("comp1").physics("temw").create("sctr1", "Scattering", 2);
    model.component("comp1").physics("temw").feature("sctr1").selection()
         .set(1, 2, 4, 5, 7, 10, 12, 29, 30, 32, 35, 40, 41);
    model.component("comp1").physics("temw").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("temw").feature("lport1").selection().set(21);
    model.component("comp1").physics("temw").feature("lport1").set("V0", "an1(t)");
    model.component("comp1").physics("temw").create("lport2", "LumpedPort", 2);
    model.component("comp1").physics("temw").feature("lport2").selection().set(38);
    model.component("comp1").physics("temw").create("lport3", "LumpedPort", 2);
    model.component("comp1").physics("temw").feature("lport3").selection().set(13);
    model.component("comp1").physics("temw").create("lport4", "LumpedPort", 2);
    model.component("comp1").physics("temw").feature("lport4").selection().set(36);
    model.component("comp1").physics("temw").prop("MeshControl")
         .set("PhysicsControlledMeshMaximumElementSize", "hm");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(18, 20, 23, 25, 28, 30);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().set(18, 20, 28, 30);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 3);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").selection().set(23, 25);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("elemcount", 4);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(13, 17, 21);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(17, 32);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(6, 9, 25);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2, 3, 4, 5, 6, 7);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature().remove("ftet1");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom(2);
    model.component("comp1").view("view1").hideEntities("hide1").add(12);
    model.component("comp1").view("view1").hideEntities("hide1").add(10);
    model.component("comp1").view("view1").hideEntities("hide1").add(4);
    model.component("comp1").view("view1").hideEntities("hide1").add(1);
    model.component("comp1").view("view1").hideEntities("hide1").add(2);

    model.study("std1").feature("time").set("tlist", "range(0,sim_time_step,sim_time_max)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("timestepgenalpha", "sim_time_step");
    model.sol("sol1").feature("t1").feature("dDef").set("linsolver", "pardiso");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "f0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Hz", 0);
    model.study("std1").feature("param").setIndex("pname", "f0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Hz", 0);
    model.study("std1").feature("param").setIndex("plistarr", "300[MHz] 600[MHz]", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 208, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").set("outertype", "none");
    model.result("pg1").set("solvertype", "none");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("outertype", "none");
    model.result("pg1").feature("mslc1").set("solvertype", "none");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("outertype", "none");
    model.result("pg1").feature("mslc1").set("solvertype", "none");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("outertype", "none");
    model.result("pg1").feature("mslc1").set("solvertype", "none");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("outertype", "none");
    model.result("pg1").feature("mslc1").set("solvertype", "none");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("outertype", "none");
    model.result("pg1").feature("mslc1").set("solvertype", "none");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{76, 2});
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("ynumber", "0");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u8f93\u5165\u7aef\u53e3\u7684\u65f6\u57df\u54cd\u5e94");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "temw.Vport_1", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "\u96c6\u603b\u7aef\u53e3 1 \u7684\u7535\u538b", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "an1(t)", 1);
    model.result("pg2").feature("glob1").setIndex("unit", "", 1);
    model.result("pg2").feature("glob1").setIndex("descr", "\u8f93\u5165\u8109\u51b2", 1);
    model.result("pg2").feature("glob1").set("linemarker", "cycle");
    model.result("pg2").feature("glob1").set("markerpos", "interp");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").set("title", "\u76f4\u901a\u7aef\u53e3\u7684\u65f6\u57df\u54cd\u5e94");
    model.result("pg3").set("legendpos", "middleleft");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").setIndex("expr", "temw.Vport_2", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u96c6\u603b\u7aef\u53e3 2 \u7684\u7535\u538b", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "an1(t-delay)", 1);
    model.result("pg3").feature("glob1").setIndex("descr", "\u5ef6\u8fdf\u7684\u8f93\u5165\u8109\u51b2", 1);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u8026\u5408\u7aef\u53e3\u7684\u65f6\u57df\u54cd\u5e94");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "temw.Vport_3", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u96c6\u603b\u7aef\u53e3 3 \u7684\u7535\u538b", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "temw.Vport_4", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "\u96c6\u603b\u7aef\u53e3 4 \u7684\u7535\u538b", 1);
    model.result("pg4").feature("glob1").set("linemarker", "cycle");
    model.result("pg4").feature("glob1").set("markerpos", "interp");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u8f93\u5165\u8109\u51b2\u7684\u9891\u57df\u54cd\u5e94");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "an1(t)", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u8f93\u5165\u8109\u51b2\u9891\u8c31", 0);
    model.result("pg5").feature("glob1").set("xdata", "fourier");
    model.result("pg5").feature("glob1").set("fouriershow", "spectrum");
    model.result("pg5").feature("glob1").set("freqrangeactive", true);
    model.result("pg5").feature("glob1").set("freqmax", "10[GHz]");
    model.result("pg5").feature("glob1").set("linemarker", "cycle");
    model.result("pg5").feature("glob1").set("markerpos", "interp");
    model.result("pg5").run();
    model.result("pg5").set("ylog", true);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset2");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"temw.Zport_1"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u96c6\u603b\u7aef\u53e3\u201c1\u201d\u963b\u6297"});
    model.result("pg6").feature("glob1").set("linemarker", "cycle");
    model.result("pg6").feature("glob1").set("markerpos", "interp");
    model.result("pg6").run();

    model.title("\u76f8\u90bb\u5fae\u5e26\u7ebf\u7684\u4fe1\u53f7\u5b8c\u6574\u6027\u548c TDR \u5206\u6790");

    model
         .description("\u4fe1\u53f7\u5b8c\u6574\u6027 (SI) \u5206\u6790\u7b80\u8981\u6982\u8ff0\u4e86\u901a\u8fc7\u7535\u8def\uff08\u4f8b\u5982\uff0c\u9ad8\u901f\u4e92\u8fde\u7ebf\u3001\u7535\u7f06\u53ca\u5370\u5237\u7535\u8def\u677f\uff09\u4f20\u8f93\u7684\u7535\u4fe1\u53f7\u7684\u8d28\u91cf\u3002\u63a5\u6536\u4fe1\u53f7\u7684\u8d28\u91cf\u53ef\u80fd\u53d7\u7535\u8def\u5916\u90e8\u566a\u58f0\u7684\u5f71\u54cd\u800c\u5931\u771f\uff0c\u4e5f\u53ef\u80fd\u7531\u4e8e\u963b\u6297\u4e0d\u5339\u914d\u3001\u5d4c\u5165\u635f\u8017\u548c\u4e32\u6270\u4f7f\u8d28\u91cf\u964d\u4f4e\uff1b\u5b9e\u9645\u4e0a\uff0c\u8fd0\u884c EMC/EMI \u5206\u6790\u7684\u76ee\u7684\u662f\u4f30\u7b97\u5668\u4ef6\u6216\u7f51\u7edc\u5bf9\u4e0d\u5fc5\u8981\u8026\u5408\u7684\u78c1\u5316\u7387\u3002\u5728\u6b64\u793a\u4f8b\u6a21\u578b\u4e2d\uff0c\u6211\u4eec\u5c06\u89c2\u5bdf\u5fae\u6ce2\u57fa\u7247\u4e0a\u4e24\u4e2a\u76f8\u90bb\u5fae\u5e26\u7ebf\u4e4b\u95f4\u7684\u4e32\u6270\u6548\u5e94\u3002\u4eff\u771f\u7ed3\u679c\u63d0\u4f9b\u4e86\u8026\u5408\u7aef\u53e3\u7684\u65f6\u57df\u53cd\u5c04\u6cd5 (TDR) \u54cd\u5e94\u60c5\u51b5\uff0c\u5e76\u663e\u793a\u4fe1\u53f7\u5728\u6570\u636e\u4f20\u8f93\u901f\u7387\u8f83\u9ad8\u65f6\u5931\u771f\u66f4\u660e\u663e\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("microstrip_line_crosstalk.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
