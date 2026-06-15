/*
 * sound_radiation_circular_duct.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class sound_radiation_circular_duct {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Aeroacoustics_and_Noise");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("lpff", "LinearizedPotentialFlowFrequency", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/lpff", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("m", "1", "\u65b9\u4f4d\u89d2\u6a21\u5f0f\u6570");
    model.param().set("f0", "6000[Hz]", "\u9891\u7387");
    model.param().set("U0", "40[m/s]", "\u80cc\u666f\u6d41\u901f");
    model.param().set("M0", "0.12", "\u9a6c\u8d6b\u6570");
    model.param().set("c0", "U0/M0", "\u58f0\u901f");
    model.param().set("rho0", "1.25[kg/m^3]", "\u5bc6\u5ea6");
    model.param().set("a", "0.085[m]", "\u5bfc\u7ba1\u534a\u5f84");
    model.param().set("L", "1[m]", "\u5bfc\u7ba1\u957f\u5ea6");
    model.param().set("R0", "2[m]", "\u5916\u573a\u534a\u5f84");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "1.4*R0");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "0.2*R0", 0);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "0.2*R0", 1);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"a", "1.5*R0"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-1.5*R0"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "L", 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c1", "r1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", new String[]{"c0"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 12);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "(c0-U0)/f0/6");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "(c0-U0)/f0/10");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", 3);
    model.component("comp1").mesh("mesh1").create("bl2", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl2").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("bl2").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").selection().set(10);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blhminfact", 3);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(2, 3, 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "(c0-U0)/f0/6");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(6, 7, 12);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("lpff").prop("EquationSettings").set("m", "m");
    model.component("comp1").physics("lpff").prop("PortSweepSettings").set("ModeShapeNormalization", "Power");
    model.component("comp1").physics("lpff").feature("lpfm1").set("minput_velocity", new String[]{"0", "0", "-U0"});
    model.component("comp1").physics("lpff").create("ishb1", "InteriorSoundHard", 1);
    model.component("comp1").physics("lpff").feature("ishb1").selection().set(10);
    model.component("comp1").physics("lpff").create("port1", "Port", 1);
    model.component("comp1").physics("lpff").feature("port1").selection().set(2);
    model.component("comp1").physics("lpff").feature("port1").set("PortType", "Circular");
    model.component("comp1").physics("lpff").feature("port1").set("PortExcitation", "on");
    model.component("comp1").physics("lpff").feature("port1").set("IncidentWave", "ModeScale");
    model.component("comp1").physics("lpff").feature("port1").set("Sin", 1);
    model.component("comp1").physics("lpff").create("port2", "Port", 1);
    model.component("comp1").physics("lpff").feature("port2").selection().set(2);
    model.component("comp1").physics("lpff").feature("port2").set("PortType", "Circular");
    model.component("comp1").physics("lpff").feature("port2").set("n_circ", 1);
    model.component("comp1").physics("lpff").create("port3", "Port", 1);
    model.component("comp1").physics("lpff").feature("port3").selection().set(2);
    model.component("comp1").physics("lpff").feature("port3").set("PortType", "Circular");
    model.component("comp1").physics("lpff").feature("port3").set("n_circ", 2);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(4, 5);
    model.component("comp1").coordSystem("pml1").set("PMLgamma", "3");

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u58f0\u538b (lpff)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "lpff.p");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u58f0\u538b\u7ea7 (lpff)");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "lpff.Lp");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u58f0\u538b\uff0c\u4e09\u7ef4 (lpff)");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "lpff.p");
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u4e09\u7ef4\u58f0\u538b\u7ea7 (lpff)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("data", "rev1");
    model.result("pg4").feature("surf1").setIndex("looplevel", 1, 0);
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "lpff.Lp");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "rev1");
    model.result("pg1").run();
    model.result("pg1").selection().geom("geom1", 2);
    model.result("pg1").selection().geom("geom1", 2);
    model.result("pg1").selection().set(1, 2, 3, 6);
    model.result("pg1").set("applyselectiontodatasetedges", true);
    model.result("pg1").run();
    model.result("pg2").set("applyselectiontodatasetedges", false);
    model.result("pg2").run();
    model.result("pg2").selection().geom("geom1", 2);
    model.result("pg2").selection().geom("geom1", 2);
    model.result("pg2").selection().set(1, 2, 3, 6);
    model.result("pg2").set("applyselectiontodatasetedges", true);
    model.result("pg2").run();
    model.result().dataset("rev1").set("hasspacevars", true);
    model.result().dataset("rev1").selection().geom("geom1", 2);
    model.result().dataset("rev1").selection().geom("geom1", 2);
    model.result().dataset("rev1").selection().set(1, 2);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "lpff.p*exp(i*m*rev1phi)");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PolarGroup");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("rmax", 80);
    model.result("pg5").set("symmetricangle", true);
    model.result("pg5").set("zeroangle", "up");
    model.result("pg5").set("rotdir", "cw");
    model.result("pg5").set("manualgrid", true);
    model.result("pg5").set("tspacing", 10);
    model.result("pg5").set("rspacing", 16);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().set(13, 18);
    model.result("pg5").feature("lngr1").set("expr", "lpff.Lp+20*log10(sqrt(2))[dB]");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "atan2(z,r)");
    model.result("pg5").run();
    model.result().dataset().duplicate("rev2", "rev1");
    model.result().dataset("rev2").selection().geom("geom1", 2);
    model.result().dataset("rev2").selection().set(2);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").set("data", "rev2");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("slc1", "Slice");
    model.result("pg6").feature("slc1").set("expr", "lpff.p*exp(i*m*rev1phi)");
    model.result("pg6").feature("slc1").set("quickplane", "xy");
    model.result("pg6").feature("slc1").set("quickzmethod", "coord");
    model.result("pg6").feature("slc1").set("quickz", "-0.1 0.5");
    model.result("pg6").feature("slc1").set("colortable", "Wave");
    model.result("pg6").feature("slc1").set("colorscalemode", "linearsymmetric");
    model.result("pg6").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"lpff.port1.P_in"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u5165\u5c04\u6a21\u5f0f\u7684\u529f\u7387"});
    model.result().evaluationGroup("eg1").feature("gev1").set("unit", new String[]{"W"});
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "lpff.port1.P_out", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "lpff.port2.P_out", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "lpff.port3.P_out", 3);
    model.result().evaluationGroup("eg1").run();
    model.result("pg4").run();

    model.title("\u5e26\u6d41\u4f53\u6d41\u52a8\u7684\u5706\u5f62\u5bfc\u7ba1\u7684\u58f0\u8f90\u5c04");

    model
         .description("\u672c\u6a21\u578b\u63cf\u8ff0\u5e26\u5747\u5300\u6d41\u52a8\u7684\u5706\u5f62\u5bfc\u7ba1\u7684\u58f0\u8f90\u5c04\u73b0\u8c61\uff0c\u5e76\u4f7f\u7528\u5728\u9891\u57df\u6c42\u89e3\u7684\u7ebf\u6027\u52bf\u6d41\u65b9\u7a0b\u6765\u63cf\u8ff0\u5bf9\u6d41\u58f0\u5b66\u95ee\u9898\u3002\u5728\u58f0\u5b66\u5165\u53e3\u5904\u7406\u8fc7\u7a0b\u4e2d\uff0c\u4f7f\u7528\u201c\u7aef\u53e3\u201d\u8fb9\u754c\u6761\u4ef6\u6765\u8003\u8651\u9ad8\u9636\u6a21\u6001\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("sound_radiation_circular_duct.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
