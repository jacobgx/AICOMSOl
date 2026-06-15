/*
 * sic_diode_breakdown.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:51 by COMSOL 6.3.0.290. */
public class sic_diode_breakdown {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Device_Building_Blocks");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.study().create("std1");
    model.study("std1").create("semie", "SemiconductorEquilibrium");
    model.study("std1").feature("semie").set("ftplistmethod", "manual");
    model.study("std1").feature("semie").set("solnum", "auto");
    model.study("std1").feature("semie").set("notsolnum", "auto");
    model.study("std1").feature("semie").set("outputmap", new String[]{});
    model.study("std1").feature("semie").set("ngenAUX", "1");
    model.study("std1").feature("semie").set("goalngenAUX", "1");
    model.study("std1").feature("semie").set("ngenAUX", "1");
    model.study("std1").feature("semie").set("goalngenAUX", "1");
    model.study("std1").feature("semie").setSolveFor("/physics/semi", true);

    model.param().set("V_reverse", "0[V]");
    model.param().descr("V_reverse", "Reverse bias");
    model.param().set("T_lattice", "900[K]");
    model.param().descr("T_lattice", "Lattice temperature");
    model.param().set("I_stop", "10[nA]");
    model.param().descr("I_stop", "Current level for stop condition");
    model.param().set("d_cont2edg", "3[\u00b5m]");
    model.param().descr("d_cont2edg", "Distance between contact and edge");
    model.param().set("W_anode", "25[\u00b5m]");
    model.param().descr("W_anode", "Width of anode");
    model.param().set("W_device", "60[\u00b5m]");
    model.param().descr("W_device", "Width of device");
    model.param().set("H_device", "26[\u00b5m]");
    model.param().descr("H_device", "Height of device");
    model.param().set("W_etch1", "3[\u00b5m]");
    model.param().descr("W_etch1", "Width of first etch");
    model.param().set("D_etch1", "4[\u00b5m]");
    model.param().set("W_ring1", "4[\u00b5m]");
    model.param().descr("W_ring1", "Width of first ring");
    model.param().set("d_box", "0.5[\u00b5m]");
    model.param().set("D_p_cont", "1[\u00b5m]");
    model.param().set("D_p_anode", "1.5[\u00b5m]");
    model.param().set("D_p_drift", "0.4[\u00b5m]");
    model.param().set("R_final_etch", "W_anode+W_etch1+W_ring1");
    model.param().descr("R_final_etch", "Radial position of final etch");
    model.param().set("H_substrate", "3[\u00b5m]");
    model.param().descr("H_substrate", "Height of substrate");
    model.param().set("D_p_region", "D_p_cont+D_p_anode+D_p_drift");
    model.param().descr("D_p_region", "Depth of pn-junction");
    model.param().set("t_ox", "0.5[\u00b5m]");
    model.param().descr("t_ox", "Oxide thickness");
    model.param().create("par2");
    model.param("par2").set("use_cathode_state", "0");
    model.param("par2").descr("use_cathode_state", "Use state variable for cathode voltage");

    model.common().create("state1", "StateVariables", "");
    model.common("state1").setIndex("state", "V_c_state", 0);
    model.common("state1").setIndex("initialValue", "", 0);
    model.common("state1").setIndex("updateExpression", "", 0);
    model.common("state1").setIndex("description", "", 0);
    model.common("state1").setIndex("initialValue", "comp1.semi.V0_cathode", 0);
    model.common("state1").setIndex("updateExpression", "comp1.semi.V0_cathode", 0);
    model.common("state1").setIndex("description", "Cathode voltage", 0);
    model.common("state1").set("update", "afterStep");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W_device", "H_device"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-H_device"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layername", "p-contact", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "D_p_cont", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layername", "p-anode", 1);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "D_p_anode", 1);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layername", "p-drift", 2);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "D_p_drift", 2);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layername", "n-etch", 3);
    model.component("comp1").geom("geom1").feature("r1")
         .setIndex("layer", "D_etch1-D_p_cont-D_p_anode-D_p_drift+d_box", 3);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layername", "n-drift", 4);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "H_device-H_substrate-D_etch1-d_box", 4);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"W_etch1+2*d_box", "1"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("size", "D_etch1+d_box", 1);
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"W_anode-d_box", "0"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("pos", "-D_etch1-d_box", 1);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"W_etch1", "D_etch1"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"W_anode", "-D_etch1"});
    model.component("comp1").geom("geom1").feature("r3").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"W_device-R_final_etch", "1"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("size", "D_etch1", 1);
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"R_final_etch", "0"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("pos", "-D_etch1", 1);
    model.component("comp1").geom("geom1").feature("r4").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5")
         .set("size", new String[]{"W_device+d_box-R_final_etch", "1"});
    model.component("comp1").geom("geom1").feature("r5").setIndex("size", "D_etch1+d_box", 1);
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"R_final_etch-d_box", "0"});
    model.component("comp1").geom("geom1").feature("r5").setIndex("pos", "-D_etch1-d_box", 1);
    model.component("comp1").geom("geom1").feature("r5").setIndex("layer", "2*d_box+1", 0);
    model.component("comp1").geom("geom1").feature("r5").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r5").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r3", 1, 2);
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r4", 1);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 1);
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1", "r2", "r5");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("fil1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "W_anode-d_cont2edg", 0);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", -1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", "W_device+1");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "-D_p_cont-0.1");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", 0.1);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmin", -1);
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "W_device+1");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymin", "-D_p_anode-D_p_drift-D_p_cont-0.1");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymax", "-D_p_cont+0.1");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmin", -1);
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmax", "W_device+1");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymin", "-H_device+H_substrate-0.1");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymax", "-D_p_region+0.1");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmin", -1);
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmax", "W_device+1");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymin", "-H_device-0.1");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymax", "-H_device+H_substrate+0.1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("dif1");
    model.component("comp1").geom("geom1").feature("sel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"sel1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("boxsel5", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel5").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel5").set("xmin", 1);
    model.component("comp1").geom("geom1").feature("boxsel5").set("xmax", 49);
    model.component("comp1").geom("geom1").feature("boxsel5").set("ymin", -4.2);
    model.component("comp1").geom("geom1").feature("boxsel5").set("selshow", false);
    model.component("comp1").geom("geom1").run("boxsel5");
    model.component("comp1").geom("geom1").create("intsel1", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("intsel1").set("input", new String[]{"adjsel1", "boxsel5"});
    model.component("comp1").geom("geom1").feature("intsel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("intsel1");
    model.component("comp1").geom("geom1").create("off1", "Offset");
    model.component("comp1").geom("geom1").feature("off1").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("off1").selection("input").named("intsel1");
    model.component("comp1").geom("geom1").feature("off1").set("distance", "t_ox");
    model.component("comp1").geom("geom1").run("off1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "t_ox");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("pt1");
    model.component("comp1").geom("geom1").feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("off1", 1);
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex2").set("dif1", 7);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex1").set("pt1", 1);
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex2").set("mov1", 1);
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").selection("vertex1").set("off1", 17);
    model.component("comp1").geom("geom1").feature("ls3").selection("vertex2").set("dif1", 29);
    model.component("comp1").geom("geom1").run("ls3");
    model.component("comp1").geom("geom1").create("ls4", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls4").selection("vertex1").set("off1", 18);
    model.component("comp1").geom("geom1").feature("ls4").selection("vertex2").set("dif1", 34);
    model.component("comp1").geom("geom1").run("ls4");
    model.component("comp1").geom("geom1").create("ls5", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls5").selection("vertex1").set("off1", 27);
    model.component("comp1").geom("geom1").feature("ls5").selection("vertex2").set("dif1", 46);
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter("mov1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ls1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ls2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ls3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ls4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ls5");
    model.component("comp1").geom("geom1").run("ls5");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").nodeGroup("grp1").remove("csol1", false);
    model.component("comp1").geom("geom1").feature("csol1").selection("input")
         .set("dif1", "ls1", "ls2", "ls3", "ls4", "ls5", "mov1", "off1");
    model.component("comp1").geom("geom1").run("csol1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("csol1", 7, 20);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("cha1", "Chamfer");
    model.component("comp1").geom("geom1").feature("cha1").selection("point").set("del1", 9, 47, 53);
    model.component("comp1").geom("geom1").feature("cha1").set("dist", "t_ox");
    model.component("comp1").geom("geom1").run("cha1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("cha1");
    model.component("comp1").geom("geom1").feature("sel2").set("selshow", false);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"sel2"});
    model.component("comp1").geom("geom1").feature("adjsel2").set("selshow", false);
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("boxsel6", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel6").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel6").set("xmin", 1);
    model.component("comp1").geom("geom1").feature("boxsel6").set("xmax", 26.5);
    model.component("comp1").geom("geom1").feature("boxsel6").set("ymin", -3.8);
    model.component("comp1").geom("geom1").feature("boxsel6").set("selshow", false);
    model.component("comp1").geom("geom1").run("boxsel6");
    model.component("comp1").geom("geom1").create("intsel2", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("intsel2").set("input", new String[]{"boxsel6", "adjsel2"});
    model.component("comp1").geom("geom1").run("intsel2");
    model.component("comp1").geom("geom1").create("boxsel7", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel7").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel7").set("xmin", 27.8);
    model.component("comp1").geom("geom1").feature("boxsel7").set("xmax", 35);
    model.component("comp1").geom("geom1").feature("boxsel7").set("ymin", -3.8);
    model.component("comp1").geom("geom1").feature("boxsel7").set("ymax", 1);
    model.component("comp1").geom("geom1").feature("boxsel7").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel7").set("selshow", false);
    model.component("comp1").geom("geom1").run("boxsel7");
    model.component("comp1").geom("geom1").create("intsel3", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("intsel3").set("input", new String[]{"adjsel2", "boxsel7"});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("AroraMobilityModel", "AroraMobilityModel", "Arora mobility model");
    model.component("comp1").material("mat1").propertyGroup().create("Auger", "Auger", "Auger recombination");
    model.component("comp1").material("mat1").propertyGroup().create("Direct", "Direct", "Direct recombination");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SRH", "SRH", "Shockley\u2013Read\u2013Hall recombination");
    model.component("comp1").material("mat1").propertyGroup()
         .create("CaugheyThomasMobilityModel", "CaugheyThomasMobilityModel", "Caughey\u2013Thomas mobility model");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SemicondMaterial", "SemicondMaterial", "Semiconductor material");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ImpactIonization", "ImpactIonization", "Impact ionization");
    model.component("comp1").material("mat1").label("SiC - Silicon Carbide [solid,4H Polytype]");
    model.component("comp1").material("mat1").set("phase", "solid");
    model.component("comp1").material("mat1").set("orientation", "4H Polytype");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Thermal conductivity");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "kappa");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "b0*(T/300)^(-1.49)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T", "b0"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"K", "W/(cm*K)"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"on", "off"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"300", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "300", "600"}, {"b0", "1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .label("Integral for Debye Approximation");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("funcname", "Cp_debye_int");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"1.00E-04", "1.00E-08"}, 
         {"2.00E-04", "4.00E-08"}, 
         {"5.00E-04", "2.50E-07"}, 
         {"1.00E-03", "1.00E-06"}, 
         {"2.00E-03", "4.00E-06"}, 
         {"5.00E-03", "2.50E-05"}, 
         {"1.00E-02", "1.00E-04"}, 
         {"2.00E-02", "4.00E-04"}, 
         {"5.00E-02", "2.50E-03"}, 
         {"1.00E-01", "9.99E-03"}, 
         {"2.00E-01", "3.99E-02"}, 
         {"3.00E-01", "8.93E-02"}, 
         {"4.00E-01", "1.58E-01"}, 
         {"5.00E-01", "2.45E-01"}, 
         {"6.00E-01", "3.49E-01"}, 
         {"7.00E-01", "4.70E-01"}, 
         {"8.00E-01", "6.07E-01"}, 
         {"9.00E-01", "7.57E-01"}, 
         {"1.00E+00", "9.21E-01"}, 
         {"1.10E+00", "1.10E+00"}, 
         {"1.20E+00", "1.28E+00"}, 
         {"1.30E+00", "1.47E+00"}, 
         {"1.40E+00", "1.67E+00"}, 
         {"1.50E+00", "1.87E+00"}, 
         {"1.60E+00", "2.08E+00"}, 
         {"1.70E+00", "2.28E+00"}, 
         {"1.80E+00", "2.49E+00"}, 
         {"1.90E+00", "2.70E+00"}, 
         {"2.00E+00", "2.90E+00"}, 
         {"3.00E+00", "4.47E+00"}, 
         {"4.00E+00", "4.87E+00"}, 
         {"5.00E+00", "4.27E+00"}, 
         {"6.00E+00", "3.23E+00"}, 
         {"7.00E+00", "2.19E+00"}, 
         {"8.00E+00", "1.37E+00"}, 
         {"9.00E+00", "8.10E-01"}, 
         {"1.00E+01", "4.54E-01"}, 
         {"1.10E+01", "2.45E-01"}, 
         {"1.20E+01", "1.27E-01"}, 
         {"1.30E+01", "6.46E-02"}, 
         {"1.40E+01", "3.19E-02"}, 
         {"1.50E+01", "1.55E-02"}, 
         {"1.60E+01", "7.38E-03"}, 
         {"1.70E+01", "3.46E-03"}, 
         {"1.80E+01", "1.60E-03"}, 
         {"1.90E+01", "7.30E-04"}, 
         {"2.00E+01", "3.30E-04"}, 
         {"2.10E+01", "1.47E-04"}, 
         {"2.20E+01", "6.53E-05"}, 
         {"2.30E+01", "2.87E-05"}, 
         {"2.40E+01", "1.25E-05"}, 
         {"2.50E+01", "5.42E-06"}, 
         {"2.60E+01", "2.33E-06"}, 
         {"2.70E+01", "9.99E-07"}, 
         {"2.80E+01", "4.25E-07"}, 
         {"2.90E+01", "1.80E-07"}, 
         {"3.00E+01", "7.58E-08"}, 
         {"3.10E+01", "3.18E-08"}, 
         {"3.20E+01", "1.33E-08"}, 
         {"3.30E+01", "5.53E-09"}, 
         {"3.40E+01", "2.29E-09"}, 
         {"3.50E+01", "9.46E-10"}, 
         {"3.60E+01", "3.90E-10"}, 
         {"3.70E+01", "1.60E-10"}, 
         {"3.80E+01", "6.55E-11"}, 
         {"3.90E+01", "2.67E-11"}, 
         {"4.00E+01", "1.09E-11"}, 
         {"1.00E-04", "1.00E-08"}, 
         {"2.00E-04", "4.00E-08"}, 
         {"5.00E-04", "2.50E-07"}, 
         {"1.00E-03", "1.00E-06"}, 
         {"2.00E-03", "4.00E-06"}, 
         {"5.00E-03", "2.50E-05"}, 
         {"1.00E-02", "1.00E-04"}, 
         {"2.00E-02", "4.00E-04"}, 
         {"5.00E-02", "2.50E-03"}, 
         {"1.00E-01", "9.99E-03"}, 
         {"2.00E-01", "3.99E-02"}, 
         {"3.00E-01", "8.93E-02"}, 
         {"4.00E-01", "1.58E-01"}, 
         {"5.00E-01", "2.45E-01"}, 
         {"6.00E-01", "3.49E-01"}, 
         {"7.00E-01", "4.70E-01"}, 
         {"8.00E-01", "6.07E-01"}, 
         {"9.00E-01", "7.57E-01"}, 
         {"1.00E+00", "9.21E-01"}, 
         {"1.10E+00", "1.10E+00"}, 
         {"1.20E+00", "1.28E+00"}, 
         {"1.30E+00", "1.47E+00"}, 
         {"1.40E+00", "1.67E+00"}, 
         {"1.50E+00", "1.87E+00"}, 
         {"1.60E+00", "2.08E+00"}, 
         {"1.70E+00", "2.28E+00"}, 
         {"1.80E+00", "2.49E+00"}, 
         {"1.90E+00", "2.70E+00"}, 
         {"2.00E+00", "2.90E+00"}, 
         {"3.00E+00", "4.47E+00"}, 
         {"4.00E+00", "4.87E+00"}, 
         {"5.00E+00", "4.27E+00"}, 
         {"6.00E+00", "3.23E+00"}, 
         {"7.00E+00", "2.19E+00"}, 
         {"8.00E+00", "1.37E+00"}, 
         {"9.00E+00", "8.10E-01"}, 
         {"1.00E+01", "4.54E-01"}, 
         {"1.10E+01", "2.45E-01"}, 
         {"1.20E+01", "1.27E-01"}, 
         {"1.30E+01", "6.46E-02"}, 
         {"1.40E+01", "3.19E-02"}, 
         {"1.50E+01", "1.55E-02"}, 
         {"1.60E+01", "7.38E-03"}, 
         {"1.70E+01", "3.46E-03"}, 
         {"1.80E+01", "1.60E-03"}, 
         {"1.90E+01", "7.30E-04"}, 
         {"2.00E+01", "3.30E-04"}, 
         {"2.10E+01", "1.47E-04"}, 
         {"2.20E+01", "6.53E-05"}, 
         {"2.30E+01", "2.87E-05"}, 
         {"2.40E+01", "1.25E-05"}, 
         {"2.50E+01", "5.42E-06"}, 
         {"2.60E+01", "2.33E-06"}, 
         {"2.70E+01", "9.99E-07"}, 
         {"2.80E+01", "4.25E-07"}, 
         {"2.90E+01", "1.80E-07"}, 
         {"3.00E+01", "7.58E-08"}, 
         {"3.10E+01", "3.18E-08"}, 
         {"3.20E+01", "1.33E-08"}, 
         {"3.30E+01", "5.53E-09"}, 
         {"3.40E+01", "2.29E-09"}, 
         {"3.50E+01", "9.46E-10"}, 
         {"3.60E+01", "3.90E-10"}, 
         {"3.70E+01", "1.60E-10"}, 
         {"3.80E+01", "6.55E-11"}, 
         {"3.90E+01", "2.67E-11"}, 
         {"4.00E+01", "1.09E-11"}, 
         {"", ""}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("fununit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("defineprimfun", true);
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("primfunname", "Cp_debye_int_prim");
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "0.877e6[cm/s]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"9.76", "0", "0", "0", "9.76", "0", "0", "0", "9.98"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("relpermittivity", "Using values from 6H-SiC");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"kappa(T,b0_a)", "0", "0", "0", "kappa(T,b0_a)", "0", "0", "0", "kappa(T,b0_c)"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "Using values from 6H-SiC");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp_debye/rho");
    model.component("comp1").material("mat1").propertyGroup("def").set("b0_a", "3.74[W/(cm*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .descr("b0_a", "Thermal conductivity, b0 coefficient, perpenducalr to c-axis");
    model.component("comp1").material("mat1").propertyGroup("def").set("b0_c", "2.62[W/(cm*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .descr("b0_c", "Thermal conductivity, b0 coefficient, parallel to c-axis");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("Cp_debye", "12*pi*(k_B_const*T/(h_const*c))^3*k_B_const*(Cp_debye_int_prim(T_debye/T)-Cp_debye_int_prim(0))");
    model.component("comp1").material("mat1").propertyGroup("def")
         .descr("Cp_debye", "Cp per volume, Debye approximation");
    model.component("comp1").material("mat1").propertyGroup("def").set("T_debye", "1200[K]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("T_debye", "Debye temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").label("Arora mobility model");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("mun0_ref_arora", "1141[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .setPropertyInfo("mun0_ref_arora", "Parallel to c: 1141\nPerpendicualr to c: 947");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("mup0_ref_arora", "124[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("mun_min_ref_arora", "0[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("mup_min_ref_arora", "15.9[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("Nn0_ref_arora", "1.94e17[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("Np0_ref_arora", "1.76e19[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("alpha0_arora", "0.61");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .setPropertyInfo("alpha0_arora", "n-type: 0.61\np-type: 0.34");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("beta1_arora", "0");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("beta2_arora", "-2.15");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .setPropertyInfo("beta2_arora", "n-type, parallel to c: -2.40\nn-type, perpendicular to c: -2.15\np-type: -2.15");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("beta3_arora", "0");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("beta4_arora", "0");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("Tref_arora", "300[K]");
    model.component("comp1").material("mat1").propertyGroup("Auger").label("Auger recombination");
    model.component("comp1").material("mat1").propertyGroup("Auger").set("Cn", "5e-31[cm^6/s]");
    model.component("comp1").material("mat1").propertyGroup("Auger").set("Cp", "5e-31[cm^6/s]");
    model.component("comp1").material("mat1").propertyGroup("Auger").set("y30", "7e-31[cm^6/s]");
    model.component("comp1").material("mat1").propertyGroup("Auger")
         .descr("y30", "Temperature dependent coefficient");
    model.component("comp1").material("mat1").propertyGroup("Auger").set("E0", "0.45[eV]");
    model.component("comp1").material("mat1").propertyGroup("Auger").descr("E0", "Exponential coefficient");
    model.component("comp1").material("mat1").propertyGroup("Auger").addInput("temperature");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat1").propertyGroup("Direct").label("Direct recombination");
    model.component("comp1").material("mat1").propertyGroup("Direct").set("C", "0[cm^3/s]");
    model.component("comp1").material("mat1").propertyGroup("SRH")
         .label("Shockley\u2013Read\u2013Hall recombination");
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taun", "1[us]");
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taup", "1[us]");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel")
         .label("Caughey\u2013Thomas mobility model");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("alphan0_ct", "2");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("alphap0_ct", "2");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("vn0_ct", "2e7[cm/s]");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("vp0_ct", "2e7[cm/s]");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("betan1_ct", "0");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("betap1_ct", "0");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("betan2_ct", "0");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("betap2_ct", "0");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("Tref_ct", "300[K]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").label("Semiconductor material");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Eg0", "3.26[V]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("chi0", "3.1[V]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Nv", "(T/300[K])^(3/2)*Nv0");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Nc", "(T/300[K])^(3/2)*Nc0");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mun", "1141[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .setPropertyInfo("mun", "Max mobility from Aurora model");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mup", "140[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .setPropertyInfo("mup", "Max mobility from Aurora model");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mde", "0.76[1]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .descr("mde", "Relative effective mass of electrons in the conduction band");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mdh", "1.20[1]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .descr("mdh", "Relative effective mass of holes in the valence band");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nc0", "2.509e19*mde^(3/2)[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .descr("Nc0", "Reference density of states in the conduction band");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nv0", "2.509e19*mdh^(3/2)[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .descr("Nv0", "Reference density of states in the valence band");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").label("Impact ionization");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("an", "a_n");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("ap", "a_p");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("bn", "b_n");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("bp", "b_p");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("cnii", "0[1/K]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("cpii", "0[1/K]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("dn", "0[1/K]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("dp", "0[1/K]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("eps_i_e", "10[eV]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization")
         .descr("eps_i_e", "Electron coefficient");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("eps_r", "0.12[eV]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").descr("eps_r", "Common coefficient");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("lambda_e", "29.9[\u00c5]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization")
         .descr("lambda_e", "Length coefficient, electrons");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("eps_i_h", "7[eV]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").descr("eps_i_h", "Hole coefficient");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("lambda_h", "32.5[\u00c5]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization")
         .descr("lambda_h", "Length coefficient, holes");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("a_n", "e_const/eps_i_e");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").descr("a_n", "an coefficient");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization")
         .set("b_n", "sqrt(3*eps_i_e*eps_r)/(e_const*lambda_e)");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").descr("b_n", "bn coefficient");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("a_p", "e_const/eps_i_h");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").descr("a_p", "an coefficient");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization")
         .set("b_p", "sqrt(3*eps_i_h*eps_r)/(e_const*lambda_h)");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").descr("b_p", "bn coefficient");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("SiO2 - Silicon oxide");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.5e-6[1/K]", "0", "0", "0", "0.5e-6[1/K]", "0", "0", "0", "0.5e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "730[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.2", "0", "0", "0", "4.2", "0", "0", "0", "4.2"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2200[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "70e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat2").selection().set(7, 23);

    model.component("comp1").physics("semi").prop("LatticeProperties").set("T0", "T_lattice");
    model.component("comp1").physics("semi").feature("smm1").set("minput_temperature_src", "fromCommonDef");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T_lattice"}});

    model.component("comp1").physics("semi").feature("smm1").set("Ionization", "incomplete");
    model.component("comp1").physics("semi").feature("smm1").set("deltaEd", "0.060[V]");
    model.component("comp1").physics("semi").feature("smm1").set("deltaEa", "0.190[V]");
    model.component("comp1").physics("semi").feature("smm1").create("mmar1", "AroraMobilityModel", 2);
    model.component("comp1").physics("semi").create("ccn1", "ChargeConservation", 2);
    model.component("comp1").physics("semi").feature("ccn1").selection().set(7, 23);
    model.component("comp1").physics("semi").feature("ccn1").set("minput_temperature_src", "fromCommonDef");
    model.component("comp1").physics("semi").create("iig1", "IIGeneration", 2);
    model.component("comp1").physics("semi").feature("iig1").selection().all();
    model.component("comp1").physics("semi").create("tar1", "TrapAssistedRecombination", 2);
    model.component("comp1").physics("semi").feature("tar1").selection().all();

    model.nodeGroup().create("grp1", "Physics", "semi");
    model.nodeGroup("grp1").placeAfter("ccn1");
    model.nodeGroup("grp1").add("iig1");
    model.nodeGroup("grp1").add("tar1");

    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm1").selection().set(6, 11, 14, 18, 22);
    model.component("comp1").physics("semi").feature("adm1").set("NAc", "5e19[1/cm^3]");
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm2").selection().set(4, 5, 9, 10, 12, 13, 16, 17, 20, 21);
    model.component("comp1").physics("semi").feature("adm2").set("NAc", "1e18[1/cm^3]");
    model.component("comp1").physics("semi").create("adm3", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm3").selection().set(2, 3, 8, 15, 19, 24);
    model.component("comp1").physics("semi").feature("adm3").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm3").set("NDc", "2e15[1/cm^3]");
    model.component("comp1").physics("semi").create("adm4", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm4").selection().set(1);
    model.component("comp1").physics("semi").feature("adm4").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm4").set("NDc", "2e18[1/cm^3]");

    model.nodeGroup().create("grp2", "Physics", "semi");
    model.nodeGroup("grp2").placeAfter("ccn1");
    model.nodeGroup("grp2").add("adm1");
    model.nodeGroup("grp2").add("adm2");
    model.nodeGroup("grp2").add("adm3");
    model.nodeGroup("grp2").add("adm4");

    model.component("comp1").physics("semi").create("mc1", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc1").selection().named("geom1_intsel2");
    model.component("comp1").physics("semi").feature("mc1").set("TerminalName", "anode");
    model.component("comp1").physics("semi").feature("mc1").set("V0", "-V_reverse");
    model.component("comp1").physics("semi").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("semi").feature("pot1").selection().named("geom1_intsel2");
    model.component("comp1").physics("semi").feature("pot1").set("V0", "-V_reverse");
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc2").selection().set(2);
    model.component("comp1").physics("semi").feature("mc2").set("TerminalName", "cathode");
    model.component("comp1").physics("semi").feature("mc2").set("V0", "use_cathode_state*V_c_state[V]");
    model.component("comp1").physics("semi").create("pot2", "ElectricPotential", 1);
    model.component("comp1").physics("semi").feature("pot2").selection().named("geom1_intsel3");
    model.component("comp1").physics("semi").feature("pot2").set("V0", "semi.V0_ring1");
    model.component("comp1").physics("semi").create("mc3", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc3").selection().named("geom1_intsel3");
    model.component("comp1").physics("semi").feature("mc3").set("TerminalName", "ring1");
    model.component("comp1").physics("semi").feature("mc3").set("TerminalType", "Current");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature().duplicate("size3", "size1");
    model.component("comp1").mesh("mesh1").feature().move("size3", 3);
    model.component("comp1").mesh("mesh1").feature("size3").selection()
         .set(4, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 24);
    model.component("comp1").mesh("mesh1").feature("size3").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "d_box", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "d_box", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "V_reverse", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,5,2500)", 0);
    model.study("std1").showAutoSequences("sol");

    model.sol("sol1").feature("v2").set("scalemethod", "init");
    model.sol("sol1").feature("v2").feature("comp1_semi_nJw").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_semi_nJw").set("scaleval", "1e4");
    model.sol("sol1").feature("v2").feature("comp1_V").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_V").set("scaleval", 100);
    model.sol("sol1").feature("v2").feature("comp1_semi_mc3_V0_ode").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_semi_mc3_V0_ode").set("scaleval", 10);
    model.sol("sol1").feature("s2").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s2").feature("p1").set("pinitstep", 0.05);
    model.sol("sol1").feature("s2").feature("p1").set("pminstep", 0.01);
    model.sol("sol1").feature("s2").feature("p1").set("pmaxstep", 1);
    model.sol("sol1").feature("s2").feature("p1").create("st1", "StopCondition");
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1")
         .setIndex("stopcondarr", "abs(comp1.semi.I0_cathode)>I_stop", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").set("storestopcondsol", "stepbefore");
    model.sol("sol1").feature("s2").feature("p1").feature("st1").set("stopcondwarn", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("revangle", 270);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u5b50\u6d53\u5ea6 (semi)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "semi.N");
    model.result("pg1").feature("surf1").set("unit", "1/cm^3");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg1").feature("surf1").set("resolution", "norefine");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7a7a\u7a74\u6d53\u5ea6 (semi)");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "semi.P");
    model.result("pg2").feature("surf1").set("unit", "1/cm^3");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf1").set("resolution", "norefine");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u52bf (semi)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "V");
    model.result("pg3").feature("surf1").set("resolution", "norefine");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u5b50\u6d53\u5ea6 (semi) 1");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "semi.N");
    model.result("pg4").feature("surf1").set("unit", "1/cm^3");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").set("resolution", "norefine");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7a7a\u7a74\u6d53\u5ea6 (semi) 1");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "semi.P");
    model.result("pg5").feature("surf1").set("unit", "1/cm^3");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg5").feature("surf1").set("resolution", "norefine");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7535\u52bf (semi) 1");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "V");
    model.result("pg6").feature("surf1").set("resolution", "norefine");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf2").set("expr", "semi.Nnetdop");
    model.result("pg7").feature("surf2").set("unit", "1/cm^3");
    model.result("pg7").feature("surf2").set("coloring", "gradient");
    model.result("pg7").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("surf2").set("topcolor", "red");
    model.result("pg7").feature("surf2").set("bottomcolor", "custom");
    model.result("pg7").feature("surf2").set("custombottomcolor", new double[]{1, 0.8, 0.8});
    model.result("pg7").feature("surf2").set("smooth", "internal");
    model.result("pg7").feature("surf2").set("showsolutionparams", "on");
    model.result("pg7").feature("surf2").set("data", "parent");
    model.result("pg7").feature("surf2").set("titletype", "none");
    model.result("pg7").feature("surf2").feature().create("filt1", "Filter");
    model.result("pg7").feature("surf2").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg7").feature("surf2").feature("filt1").set("useder", true);
    model.result("pg7").feature("surf1").set("expr", "semi.Nnetdop");
    model.result("pg7").feature("surf1").set("unit", "1/cm^3");
    model.result("pg7").feature("surf1").set("coloring", "gradient");
    model.result("pg7").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("surf1").set("topcolor", "blue");
    model.result("pg7").feature("surf1").set("bottomcolor", "custom");
    model.result("pg7").feature("surf1").set("custombottomcolor", new double[]{0.8, 0.8, 1});
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg7").feature("surf1").set("titletype", "none");
    model.result("pg7").feature("surf1").feature().create("filt1", "Filter");
    model.result("pg7").feature("surf1").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg7").feature("surf1").feature("filt1").set("useder", true);
    model.result("pg7").set("titletype", "manual");
    model.result("pg7")
         .set("title", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert: P \u578b (\u7ea2\u8272), N \u578b (\u84dd\u8272)");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").set("legendpos", "alternating");
    model.result("pg7").feature("surf2").label("P \u578b");
    model.result("pg7").feature("surf1").label("N \u578b");
    model.result("pg7").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi)");
    model.result("pg1").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "abs(semi.I0_cathode)", 0);
    model.result("pg8").feature("glob1").setIndex("descr", "Cathode current", 0);
    model.result("pg8").feature("glob1").setIndex("expr", "abs(semi.I0_anode)", 1);
    model.result("pg8").feature("glob1").setIndex("descr", "Anode current", 1);
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").feature("glob1").set("xdataexpr", "-V_reverse");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").run();
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "semi.normE");
    model.result("pg9").feature("surf1").set("descr", "\u7535\u573a\u6a21");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").stepFirst(0);
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("rangecoloractive", true);
    model.result("pg9").run();
    model.result("pg9").feature("surf1").stepLast(0);
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").run();
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "semi.Gii");
    model.result("pg10").feature("surf1").set("descr", "\u78b0\u649e\u7535\u79bb\u4ea7\u751f\u9879");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").set("rangecoloractive", true);
    model.result("pg10").feature("surf1").set("rangecolormax", 6.84739599908922E7);
    model.result("pg10").run();

    model.title("\u7845\u5316\u78b3\u4e8c\u6781\u7ba1\u51fb\u7a7f");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u7845\u5316\u78b3\u4e8c\u6781\u7ba1\u4e2d\u7531\u78b0\u649e\u7535\u79bb\u5bfc\u81f4\u7684\u96ea\u5d29\u51fb\u7a7f\uff0c\u5e76\u5448\u73b0\u4e86\u5668\u4ef6\u7684\u7535\u6d41-\u7535\u538b (I-V) \u7279\u6027\u4ee5\u53ca\u7535\u573a\u5206\u5e03\u56fe\u3002\u6b64\u5916\uff0c\u8fd8\u8ba1\u7b97\u4e86\u8f7d\u6d41\u5b50\u4ea7\u751f\u9879\uff0c\u4ee5\u63ed\u793a\u51fb\u7a7f\u7535\u6d41\u7684\u8def\u5f84\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("sic_diode_breakdown.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
