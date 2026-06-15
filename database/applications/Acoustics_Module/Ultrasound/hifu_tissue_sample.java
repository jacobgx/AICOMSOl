/*
 * hifu_tissue_sample.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class hifu_tissue_sample {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Ultrasound");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("nate", "NonlinearPressureAcousticsTimeExplicit", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/nate", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("P0", "0.25[MPa]", "\u6e90\u538b\u529b\u5e45\u503c");
    model.param().set("f0", "1[MHz]", "\u6e90\u9891\u7387");
    model.param().set("omega0", "2*pi*f0", "\u6e90\u89d2\u9891\u7387");
    model.param().set("T0", "1/f0", "\u6e90\u5468\u671f");
    model.param().set("alpha_water", "0.025[1/m]", "1 MHz \u6c34\u7684\u5438\u58f0\u7cfb\u6570");
    model.param().set("alpha_tissue", "8.55[1/m]", "1 MHz \u7ec4\u7ec7\u4f53\u6a21\u7684\u5438\u58f0\u7cfb\u6570");
    model.param().set("c_water", "1484[m/s]", "\u6c34\u4e2d\u7684\u58f0\u901f");
    model.param().set("c_tissue", "1568[m/s]", "\u7ec4\u7ec7\u4f53\u6a21\u4e2d\u7684\u58f0\u901f");
    model.param().set("rho_water", "1000[kg/m^3]", "\u6c34\u7684\u5bc6\u5ea6");
    model.param().set("rho_tissue", "1044[kg/m^3]", "\u7ec4\u7ec7\u4f53\u6a21\u7684\u5bc6\u5ea6");
    model.param().set("BA_water", "5.2", "\u6c34\u7684\u975e\u7ebf\u6027\u53c2\u6570");
    model.param().set("BA_tissue", "7.4", "\u6c34\u7684\u975e\u7ebf\u6027\u53c2\u6570");
    model.param()
         .set("delta_water", "2*alpha_water*c_water^3/omega0^2", "\u6c34\u7684\u58f0\u6269\u6563\u7cfb\u6570");
    model.param()
         .set("delta_tissue", "2*alpha_tissue*c_tissue^3/omega0^2", "\u7ec4\u7ec7\u4f53\u6a21\u7684\u58f0\u6269\u6563\u7cfb\u6570");
    model.param().set("F", "60[mm]", "\u7126\u8ddd");
    model.param().set("r_source", "62.64[mm]", "\u900f\u955c\u534a\u5f84");
    model.param().set("w_source", "35[mm]", "\u900f\u955c\u5149\u5708");
    model.param().set("z_tissue", "24.6[mm]", "\u7ec4\u7ec7\u4f53\u6a21\u7684\u8d77\u59cb\u4f4d\u7f6e");
    model.param().set("h_model", "95.1[mm]", "\u6a21\u578b\u603b\u9ad8\u5ea6");
    model.param().set("r_model", "43.6[mm]", "\u6a21\u578b\u603b\u534a\u5f84");
    model.param().set("th_abs", "5[mm]", "\u5438\u6536\u5c42\u539a\u5ea6");

    model.func().create("rect1", "Rectangle");
    model.func("rect1").set("lower", 0);
    model.func("rect1").set("upper", "5*T0");
    model.func("rect1").set("smoothactive", false);
    model.func().create("an1", "Analytic");
    model.func("an1").label("\u8109\u51b2");
    model.func("an1").set("funcname", "pulse");
    model.func("an1").set("expr", "sin(omega0*t)*(1 - cos(omega0*t/5))*rect1(t)");
    model.func("an1").set("args", "t");
    model.func("an1").setIndex("argunit", "s", 0);
    model.func("an1").set("fununit", "1");
    model.func("an1").setIndex("plotargs", "9*T0", 0, 2);
    model.func("an1").createPlot("pg1");

    model.result("pg1").run();
    model.result("pg1").label("\u731d\u53d1\u97f3\u8109\u51b2");
    model.result("pg1").run();
    model.result("pg1").feature("plot1").set("display", "fourier");
    model.result("pg1").feature("plot1").set("fouriershow", "spectrum");
    model.result("pg1").feature("plot1").set("scale", "multiplyperiod");
    model.result("pg1").feature("plot1").set("freqrangeactive", true);
    model.result("pg1").feature("plot1").set("freqmax", "3*f0");
    model.result("pg1").run();

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r_source");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"0", "r_source"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1")
         .set("size", new String[]{"w_source", "r_source-sqrt(r_source^2-w_source^2)"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("c1", "r1");
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"r_model+th_abs", "1"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("size", "h_model+th_abs-z_tissue", 1);
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "z_tissue"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "th_abs", 0);
    model.component("comp1").geom("geom1").feature("r2").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r2").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r2").set("layertop", true);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"r_model+th_abs", "1"});
    model.component("comp1").geom("geom1").feature("r3")
         .setIndex("size", "z_tissue-(r_source-sqrt(r_source^2-w_source^2))", 1);
    model.component("comp1").geom("geom1").feature("r3")
         .set("pos", new String[]{"0", "r_source-sqrt(r_source^2-w_source^2)"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", "th_abs", 0);
    model.component("comp1").geom("geom1").feature("r3").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r3").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("fin", 3);
    model.component("comp1").geom("geom1").run("ige1");

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1").setIndex("coords2", "z_tissue", 1);
    model.component("comp1").probe("pdom1").feature("ppb1").set("expr", "nate.p_t/P0");
    model.component("comp1").probe().create("pdom2", "DomainPoint");
    model.component("comp1").probe("pdom2").setIndex("coords2", "F", 1);
    model.component("comp1").probe("pdom2").feature("ppb2").set("expr", "nate.p_t/P0");

    model.component("comp1").coordSystem().create("ab1", "AbsorbingLayer");
    model.component("comp1").coordSystem("ab1").selection().set(3, 4, 5, 6);
    model.component("comp1").coordSystem("ab1").set("ScalingType", "Cylindrical");

    model.component("comp1").physics("nate").feature("natem1").set("FluidModel", "GeneralDissipation");
    model.component("comp1").physics("nate").create("md1", "MaterialDiscontinuity", 1);
    model.component("comp1").physics("nate").feature("md1").selection().set(3, 11);
    model.component("comp1").physics("nate").create("pr1", "Pressure", 1);
    model.component("comp1").physics("nate").feature("pr1").selection().set(18);
    model.component("comp1").physics("nate").feature("pr1").set("p0", "P0*pulse(t)");
    model.component("comp1").physics("nate").create("imp1", "Impedance", 1);
    model.component("comp1").physics("nate").feature("imp1").selection().set(6, 14, 15, 16, 17);
    model.component("comp1").physics("nate").feature("natem1").create("mmp1", "MinMaxPressure", 1);
    model.component("comp1").physics("nate").feature("natem1").feature("mmp1").selection().set(2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6c34");
    model.component("comp1").material("mat1").selection().set(1, 4);
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_water"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", new String[]{"c_water"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("AttenuationDissipationModel", "AttenuationDissipationModel", "Attenuation_and_dissipation_model");
    model.component("comp1").material("mat1").propertyGroup("AttenuationDissipationModel")
         .set("delta_diff", new String[]{"delta_water"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear_model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", new String[]{"BA_water"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7ec4\u7ec7");
    model.component("comp1").material("mat2").selection().set(2, 3, 5, 6);
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rho_tissue"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", new String[]{"c_tissue"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("AttenuationDissipationModel", "AttenuationDissipationModel", "Attenuation_and_dissipation_model");
    model.component("comp1").material("mat2").propertyGroup("AttenuationDissipationModel")
         .set("delta_diff", new String[]{"delta_tissue"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear_model");
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").set("BA", new String[]{"BA_tissue"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "c_water/f0/1.5");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(2, 3, 5, 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "c_tissue/f0/1.5");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0, T0, 55*T0)");
    model.study("std1").feature("time").set("timeadaption", "adaption");
    model.study("std1").feature("time").set("rmethod", "modify");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("timemethodexp", "ab3loc");
    model.sol("sol1").feature("t1").set("updtlvl", "factor");
    model.sol("sol1").feature("t1").feature("taDef").set("allowcoarsening", false);
    model.sol("sol1").feature("t1").feature("taDef").setIndex("eefunctime", "sqrt(comp1.pr^2+comp1.pz^2)", 0);

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").label("\u7ec6\u5316\u7f51\u683c\u89e3 1");
    model.sol("sol2").study("std1");
    model.sol("sol2").setClusterStorage("all");
    model.sol("sol1").feature("t1").feature("taDef").set("tadapsol", "sol2");

    model.component("comp1").probe("pdom1").genResult("none");
    model.component("comp1").probe("pdom2").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u58f0\u538b (nate)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 70, 0);
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("surf1").set("resolution", "custom");
    model.result("pg3").feature("surf1").set("refine", 6);
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u9762\u5185\u58f0\u901f (nate)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 70, 0);
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("expr", "nate.v_inst");
    model.result("pg4").feature("surf1").set("resolution", "custom");
    model.result("pg4").feature("surf1").set("refine", 6);
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").label("\u9762\u4e0a\u7bad\u5934");
    model.result("pg4").feature("arws1").set("color", "white");
    model.result("pg4").feature("arws1").set("data", "parent");
    model.result("pg3").run();
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").label("\u76f8\u5bf9\u538b\u529b\u63a2\u9488");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result().duplicate("pg5", "pg2");
    model.result("pg5").set("window", "window1");
    model.result("pg5").run();
    model.result("pg5").label("\u76f8\u5bf9\u538b\u529b\u63a2\u9488\uff0c\u9891\u8c31");
    model.result("pg5").set("legendpos", "upperright");
    model.result("pg5").set("window", "window1");
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("transform", "fourier");
    model.result("pg5").feature("tblp1").set("fouriershow", "spectrum");
    model.result("pg5").feature("tblp1").set("scale", "multiplyperiod");
    model.result("pg5").feature("tblp1").set("freqrangeactive", true);
    model.result("pg5").feature("tblp1").set("freqmax", "5*f0");
    model.result("pg5").set("window", "window1");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u8f74\u4e0a\u76f8\u5bf9\u538b\u529b");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevel", new int[]{53}, 0);
    model.result("pg6").set("titletype", "label");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").selection().set(1, 2);
    model.result("pg6").feature("lngr1").set("expr", "nate.p_t/P0");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "z/F");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5f52\u4e00\u5316\u6b63\u8d1f\u538b");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevelinput", "last", 0);
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().set(2);
    model.result("pg7").feature("lngr1").set("expr", "nate.p_max");
    model.result("pg7").feature("lngr1").set("descr", "\u6700\u5927\u58f0\u538b");
    model.result("pg7").feature("lngr1").set("expr", "nate.p_max/at1(0, F, nate.p_max)");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "z/F");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "p<sup>+</sup>/p<sub>max</sub>", 0);
    model.result("pg7").feature("lngr1").set("resolution", "norefine");
    model.result("pg7").feature().duplicate("lngr2", "lngr1");
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").set("expr", "nate.p_min");
    model.result("pg7").feature("lngr2").set("descr", "\u6700\u5c0f\u58f0\u538b");
    model.result("pg7").feature("lngr2").set("expr", "-nate.p_min/at1(0, F, nate.p_max)");
    model.result("pg7").feature("lngr2").setIndex("legends", "-p<sup>-</sup>/p<sub>max</sub>", 0);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").set("data", "dset2");
    model.result("pg8").label("\u5355\u5143\u6ce2\u65f6\u95f4\u5c3a\u5ea6");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "nate.wtc");
    model.result("pg8").feature("surf1").set("descr", "\u5355\u5143\u6ce2\u65f6\u95f4\u5c3a\u5ea6");
    model.result("pg8").feature("surf1").set("resolution", "norefine");
    model.result("pg8").feature("surf1").set("smooth", "none");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{14});
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{27});
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{40});
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{53});
    model.result("pg8").run();
    model.result("pg8").stepLast(0);
    model.result("pg8").run();
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{14});
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{27});
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{40});
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{53});
    model.result("pg3").run();
    model.result("pg3").stepLast(0);
    model.result("pg3").run();

    model
         .title("\u9ad8\u5f3a\u5ea6\u805a\u7126\u8d85\u58f0 (HIFU) \u5728\u7ec4\u7ec7\u4f53\u6a21\u4e2d\u7684\u4f20\u64ad");

    model
         .description("\u672c\u6559\u7a0b\u7814\u7a76\u9ad8\u5f3a\u5ea6\u805a\u7126\u8d85\u58f0 (HIFU) \u901a\u8fc7\u7ec4\u7ec7\u4f53\u6a21\u7684\u4f20\u64ad\u3002HIFU \u5e7f\u6cdb\u7528\u4e8e\u5404\u79cd\u4e0d\u540c\u7684\u751f\u7269\u533b\u5b66\u5e94\u7528\uff0c\u4f8b\u5982\u80bf\u7624\u7684\u70ed\u6d88\u878d\u3001\u7ecf\u9885 HIFU \u624b\u672f\u3001\u51b2\u51fb\u6ce2\u788e\u77f3\u672f\u7b49\u3002HIFU \u6362\u80fd\u5668\u901a\u5e38\u5305\u542b\u4e00\u4e2a\u805a\u7126\u900f\u955c\uff0c\u4f7f\u53d1\u5c04\u7684\u8d85\u58f0\u6ce2\u4fe1\u53f7\u5728\u805a\u7126\u533a\u5185\u8fbe\u5230\u6700\u9ad8\u5f3a\u5ea6\u3002\u5f53\u53d1\u5c04\u7684\u4fe1\u53f7\u5177\u6709\u8db3\u591f\u9ad8\u7684\u5e45\u5ea6\u65f6\uff0c\u975e\u7ebf\u6027\u6548\u5e94\u4f1a\u53d8\u5f97\u660e\u663e\uff0c\u4ece\u800c\u5bfc\u81f4\u5728\u4fe1\u53f7\u4f20\u64ad\u8fc7\u7a0b\u4e2d\u4ea7\u751f\u9ad8\u6b21\u8c10\u6ce2\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u975e\u7ebf\u6027\u538b\u529b\u58f0\u5b66\uff0c\u65f6\u57df\u663e\u5f0f\u201d\u7269\u7406\u573a\u63a5\u53e3\u6a21\u62df HIFU \u4fe1\u53f7\u7684\u975e\u7ebf\u6027\u4f20\u64ad\u3002\u5176\u4e2d\uff0c\u6e90\u4f4d\u7f6e\u7684\u4fe1\u53f7\u5e45\u5ea6\u8db3\u4ee5\u4ea7\u751f\u9ad8\u6b21\u8c10\u6ce2\uff0c\u4f46\u4e0d\u8db3\u4ee5\u5f62\u6210\u6fc0\u6ce2\u3002\u53d1\u5c04\u7684\u4fe1\u53f7\u662f\u4e00\u4e2a\u731d\u53d1\u97f3\u8109\u51b2\uff0c\u5b83\u5728\u4f20\u8f93\u8fc7\u7a0b\u4e2d\u53ea\u5360\u636e\u6709\u9650\u7684\u8ba1\u7b97\u57df\u3002\u901a\u8fc7\u4f7f\u7528\u201c\u81ea\u9002\u5e94\u7f51\u683c\u7ec6\u5316\u201d\u5728\u4fe1\u53f7\u4f20\u64ad\u540e\u81ea\u52a8\u91cd\u65b0\u5212\u5206\u7f51\u683c\uff0c\u4f7f\u7f51\u683c\u8db3\u591f\u7cbe\u7ec6\uff0c\u53ef\u4ee5\u6839\u636e\u9700\u8981\u89e3\u6790\u9ad8\u6b21\u8c10\u6ce2\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("hifu_tissue_sample.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
