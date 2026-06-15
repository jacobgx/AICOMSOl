/*
 * inline_induction_heater.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:18 by COMSOL 6.3.0.290. */
public class inline_induction_heater {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("spf", "TurbulentFlowAlgebraicYplus", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("rad", "SurfaceToSurfaceRadiation", "geom1");
    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mf").create("alf1", "AmperesLawFluid", 3);
    model.component("comp1").physics("mf").feature("alf1").selection().all();

    model.param().set("t_pipe", "2e-3[m]");
    model.param().descr("t_pipe", "Pipe thickness");
    model.param().set("R_pipe", "0.0254[m]");
    model.param().descr("R_pipe", "External radius of the pipes");
    model.param().set("r_pipe", "R_pipe-t_pipe");
    model.param().descr("r_pipe", "Internal radius of the pipes");
    model.param().set("R_i_pipe", "if(N_pipe == 1, R_pipe, min(R_pipe/(N_pipe)^0.75,R_pipe/2))");
    model.param().descr("R_i_pipe", "External radius of the pipes");
    model.param().set("r_i_pipe", "R_i_pipe-t_pipe");
    model.param().descr("r_i_pipe", "Internal radius of the pipes");
    model.param().set("x_i_pipe", "(R_pipe-0.95*R_i_pipe)*cos(pi/N_pipe)");
    model.param().descr("x_i_pipe", "In-plane x-coordinate of major tube center");
    model.param().set("y_i_pipe", "(R_pipe-0.95*R_i_pipe)*sin(pi/N_pipe)");
    model.param().descr("y_i_pipe", "In-plane y-coordinate of major tube center");
    model.param().set("L_pipe", "1[m]");
    model.param().descr("L_pipe", "Length of the pipes");
    model.param().set("N_pipe", "4");
    model.param().descr("N_pipe", "Number of pipes");
    model.param().set("R_coil", "1.6*R_pipe");
    model.param().descr("R_coil", "Radius of the multiturn coil");
    model.param().set("R_wire", "1e-2[m]");
    model.param().descr("R_wire", "Coil wire radius");
    model.param().set("x_coil", "0.15[m]");
    model.param().descr("x_coil", "Starting position of the coil");
    model.param().set("N_coil", "30");
    model.param().descr("N_coil", "Number of turns of the coil");
    model.param().set("L_coil", "2*R_wire*N_coil");
    model.param().descr("L_coil", "Length of the coil");
    model.param().set("I_coil", "2000[A]");
    model.param().descr("I_coil", "Current intensity in the coil");
    model.param().set("f_coil", "50[Hz]");
    model.param().descr("f_coil", "Excitation frequency of the coil");
    model.param().set("a_coil", "pi*R_wire^2");
    model.param().descr("a_coil", "Coil wire cross-sectional area");
    model.param().set("geom_coil", "2*N_pipe");
    model.param().descr("geom_coil", "Coil geometry multiplication factor");
    model.param().set("J_coil", "I_coil/(a_coil*N_coil)");
    model.param().descr("J_coil", "Current intensity in the coil");
    model.param().set("R_air", "3*R_coil");
    model.param().descr("R_air", "Radius of air domain");
    model.param().set("T_in", "20[degC]");
    model.param().descr("T_in", "Inlet fluid temperature");
    model.param().set("U_in", "flow_rate/cs_area_flow");
    model.param().descr("U_in", "Inlet fluid average velocity");
    model.param().set("cs_area_flow", "if(N_pipe==1,pi*r_pipe^2,N_pipe*pi*r_i_pipe^2)");
    model.param().descr("cs_area_flow", "Flow cross-sectional area");
    model.param().set("flow_rate", "5e-5[m^3/s]");
    model.param().descr("flow_rate", "Flow rate");
    model.param().set("sigma_410s", "1.6e6[S/m]");
    model.param().descr("sigma_410s", "Electric conductivity of Stainless steel 410s");
    model.param().set("mur_410s", "1500");
    model.param().descr("mur_410s", "Relative permeability of Stainless steel 410s");
    model.param().set("sigma_410", "1.74e6[S/m]");
    model.param().descr("sigma_410", "Electric conductivity of Stainless steel 410");
    model.param().set("mur_410", "750");
    model.param().descr("mur_410", "Relative permeability of Stainless steel 410");
    model.param().set("sigma_ud", "1.5e6[S/m]");
    model.param().descr("sigma_ud", "Electric conductivity of user-defined stainless steel");
    model.param().set("mur_ud", "1300");
    model.param().descr("mur_ud", "Relative permeability of user-defined stainless steel");
    model.param().set("sigma", "sigma_410s");
    model.param().descr("sigma", "Generic electric conductivity");
    model.param().set("mur", "mur_410s");
    model.param().descr("mur", "Generic relative permeability");
    model.param().set("sd_pipe", "1/sqrt(pi*f_coil*sigma*4*pi*1e-7[H/m]*mur)");
    model.param().descr("sd_pipe", "Skin depth of the pipe material");
    model.param().set("stol", "1e-3");
    model.param().descr("stol", "Solver tolerance");
    model.param().set("T_max_critical", "90[degC]");
    model.param().descr("T_max_critical", "Maximum critical temperature");
    model.param().set("T_min_critical", "20[degC]");
    model.param().descr("T_min_critical", "Minimum critical temperature");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").label("Inlet Plane");
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").label("Pipes Outer Cylinder");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("r", "if(N_pipe == 1, R_pipe, R_i_pipe)");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new String[]{"if(N_pipe == 1, 0, x_i_pipe)", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .setIndex("pos", "if(N_pipe == 1, 0, y_i_pipe)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", "360/N_pipe/2+180");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").label("Pipes Inner Cylinder");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2")
         .set("r", "if(N_pipe == 1, r_pipe, r_i_pipe)");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2")
         .set("pos", new String[]{"if(N_pipe == 1, 0, x_i_pipe)", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2")
         .setIndex("pos", "if(N_pipe == 1, 0, y_i_pipe)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("rot", "360/N_pipe/2+180");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3").label("Fluid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3")
         .set("r", "if(N_pipe == 1, r_pipe, r_i_pipe)");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3")
         .set("pos", new String[]{"if(N_pipe == 1, 0, x_i_pipe)", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3")
         .setIndex("pos", "if(N_pipe == 1, 0, y_i_pipe)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3").set("rot", "360/N_pipe/2+180");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c4").label("Coil Outer Cylinder");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c4").set("r", "R_coil + R_wire");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c4").set("angle", "360/N_pipe/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c5", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c5").label("Coil Inner Cylinder");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c5").set("r", "R_coil - R_wire");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c5").set("angle", "360/N_pipe/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c6", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c6").label("Air Cylinder");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c6").set("r", "R_air");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c6").set("angle", "360/N_pipe/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").label("Reflection Plane");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "if(N_pipe == 1,-R_air*cos(2*pi/N_pipe/2), 0)", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "if(N_pipe == 1,-R_air*sin(2*pi/N_pipe/2), 0)", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "R_air*cos(2*pi/N_pipe/2)", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "R_air*sin(2*pi/N_pipe/2)", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").label("Extrude Tubes");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1.c3", "wp1.dif1");
    model.component("comp1").geom("geom1").feature("ext1").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "L_pipe", 0);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Tube(s)");
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").label("Extrude Fluid");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp1.c4");
    model.component("comp1").geom("geom1").feature("ext2").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").init();
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp1.c3");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "L_pipe", 0);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("Fluid");
    model.component("comp1").geom("geom1").feature("ext2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").label("Extrude Coil Outer Cylinder");
    model.component("comp1").geom("geom1").feature("ext3").selection("input").set("wp1.c4");
    model.component("comp1").geom("geom1").feature("ext3").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "L_pipe", 0);
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("Coil Exterior");
    model.component("comp1").geom("geom1").feature("ext3").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").label("Extrude Coil Inner Cylinder");
    model.component("comp1").geom("geom1").feature("ext4").selection("input").set("wp1.c5");
    model.component("comp1").geom("geom1").feature("ext4").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "L_pipe", 0);
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("Coil Interior");
    model.component("comp1").geom("geom1").feature("ext4").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").feature().create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").label("Extrude Air Cylinder");
    model.component("comp1").geom("geom1").feature("ext5").selection("input").set("wp1.c6");
    model.component("comp1").geom("geom1").feature("ext5").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext5").setIndex("distance", "L_pipe", 0);
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").feature().create("ext6", "Extrude");
    model.component("comp1").geom("geom1").feature("ext6").label("Extrude Reflection Plane");
    model.component("comp1").geom("geom1").feature("ext6").selection("input").set("wp1.pol1");
    model.component("comp1").geom("geom1").feature("ext6").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext6").setIndex("distance", "L_pipe", 0);
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("Reflection Plane");
    model.component("comp1").geom("geom1").feature("ext6").set("contributeto", "csel5");
    model.component("comp1").geom("geom1").run("ext6");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("wp1.dif1");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").label("Coil Inlet Plane");
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").set("quickx", "x_coil");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").label("Coil Outlet Plane");
    model.component("comp1").geom("geom1").feature("wp3").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp3").set("quickx", "x_coil + L_coil");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", false);
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input")
         .set("ext1(2)", "ext2", "ext3", "ext4", "ext5", "ext6");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("par1").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("par2", "Partition");
    model.component("comp1").geom("geom1").feature("par2").selection("input").set("par1");
    model.component("comp1").geom("geom1").feature("par2").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par2").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("dif1", "Difference");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("dif1").label("Pipes");
    model.component("comp1").selection("dif1").set("add", new String[]{"geom1_csel1_dom"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"geom1_csel2_dom"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("Fluid Boundaries");
    model.component("comp1").selection("adj1").set("input", new String[]{"geom1_csel2_dom"});
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("Tube Boundaries");
    model.component("comp1").selection("adj2").set("input", new String[]{"geom1_csel1_dom"});
    model.component("comp1").selection().create("cyl1", "Cylinder");
    model.component("comp1").selection("cyl1").label("Inlet Cylinder");
    model.component("comp1").selection("cyl1").set("entitydim", 2);
    model.component("comp1").selection("cyl1").set("r", "R_coil");
    model.component("comp1").selection("cyl1").set("top", "eps");
    model.component("comp1").selection("cyl1").set("bottom", "-eps");
    model.component("comp1").selection("cyl1").set("axistype", "cartesian");
    model.component("comp1").selection("cyl1").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").selection("cyl1").set("condition", "inside");
    model.component("comp1").selection().duplicate("cyl2", "cyl1");
    model.component("comp1").selection("cyl2").label("Outlet Cylinder");
    model.component("comp1").selection("cyl2").set("pos", new String[]{"L_pipe", "0", "0"});
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("Fluid Inlet");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"adj1", "cyl1"});
    model.component("comp1").selection().duplicate("int2", "int1");
    model.component("comp1").selection("int2").label("Fluid Outlet");
    model.component("comp1").selection("int2").set("input", new String[]{"adj1", "cyl2"});
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2").label("Tube Lateral Walls");
    model.component("comp1").selection("dif2").set("entitydim", 2);
    model.component("comp1").selection("dif2").set("add", new String[]{"adj2"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"cyl1", "cyl2"});
    model.component("comp1").selection().create("dif3", "Difference");
    model.component("comp1").selection("dif3").label("Tube Lateral Walls Minus Reflection Plane");
    model.component("comp1").selection("dif3").set("entitydim", 2);
    model.component("comp1").selection("dif3").set("add", new String[]{"dif2"});
    model.component("comp1").selection("dif3").set("subtract", new String[]{"geom1_csel5_bnd"});
    model.component("comp1").selection().create("int3", "Intersection");
    model.component("comp1").selection("int3").label("Tube Inlet");
    model.component("comp1").selection("int3").set("entitydim", 2);
    model.component("comp1").selection("int3").set("input", new String[]{"adj2", "cyl1"});
    model.component("comp1").selection().duplicate("int4", "int3");
    model.component("comp1").selection("int4").label("Tube Outlet");
    model.component("comp1").selection("int4").set("input", new String[]{"adj2", "cyl2"});
    model.component("comp1").selection().create("dif4", "Difference");
    model.component("comp1").selection("dif4").label("Coil (Full Length)");
    model.component("comp1").selection("dif4").set("add", new String[]{"geom1_csel3_dom"});
    model.component("comp1").selection("dif4").set("subtract", new String[]{"geom1_csel4_dom"});
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("Box for Coil");
    model.component("comp1").selection("box1").set("xmin", "x_coil");
    model.component("comp1").selection("box1").set("xmax", "x_coil + L_coil");
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection().create("int5", "Intersection");
    model.component("comp1").selection("int5").label("Coil");
    model.component("comp1").selection("int5").set("input", new String[]{"dif4", "box1"});
    model.component("comp1").selection().create("cyl3", "Cylinder");
    model.component("comp1").selection("cyl3").set("entitydim", 1);
    model.component("comp1").selection("cyl3").set("r", "R_coil");
    model.component("comp1").selection("cyl3").set("top", "L_coil*1e-3");
    model.component("comp1").selection("cyl3").set("bottom", "-L_coil*1e-3");
    model.component("comp1").selection("cyl3").set("pos", new String[]{"x_coil", "0", "0"});
    model.component("comp1").selection("cyl3").set("axistype", "cartesian");
    model.component("comp1").selection("cyl3").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").selection("cyl3").set("condition", "inside");
    model.component("comp1").selection().duplicate("cyl4", "cyl3");
    model.component("comp1").selection("cyl4").set("r", "R_coil-R_wire");
    model.component("comp1").selection().create("dif5", "Difference");
    model.component("comp1").selection("dif5").label("Coil Start Edges");
    model.component("comp1").selection("dif5").set("entitydim", 1);
    model.component("comp1").selection("dif5").set("add", new String[]{"cyl3"});
    model.component("comp1").selection("dif5").set("subtract", new String[]{"cyl4"});
    model.component("comp1").selection().create("cyl5", "Cylinder");
    model.component("comp1").selection("cyl5").label("Domain Inlet");
    model.component("comp1").selection("cyl5").set("entitydim", 2);
    model.component("comp1").selection("cyl5").set("r", "R_air*1.01");
    model.component("comp1").selection("cyl5").set("top", "L_pipe*1e-3");
    model.component("comp1").selection("cyl5").set("bottom", "-L_pipe*1e-3");
    model.component("comp1").selection("cyl5").set("axistype", "cartesian");
    model.component("comp1").selection("cyl5").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").selection("cyl5").set("condition", "inside");
    model.component("comp1").selection().create("cyl6", "Cylinder");
    model.component("comp1").selection("cyl6").label("Domain Before Coil");
    model.component("comp1").selection("cyl6").set("r", "R_air*1.01");
    model.component("comp1").selection("cyl6").set("top", "x_coil + 1e-3*L_coil");
    model.component("comp1").selection("cyl6").set("bottom", 0);
    model.component("comp1").selection("cyl6").set("axistype", "cartesian");
    model.component("comp1").selection("cyl6").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").selection("cyl6").set("condition", "inside");
    model.component("comp1").selection().create("cyl7", "Cylinder");
    model.component("comp1").selection("cyl7").label("Domain Around Coil");
    model.component("comp1").selection("cyl7").set("r", "R_air*1.01");
    model.component("comp1").selection("cyl7").set("top", "x_coil + L_coil");
    model.component("comp1").selection("cyl7").set("bottom", "x_coil");
    model.component("comp1").selection("cyl7").set("axistype", "cartesian");
    model.component("comp1").selection("cyl7").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").selection("cyl7").set("condition", "inside");
    model.component("comp1").selection().create("cyl8", "Cylinder");
    model.component("comp1").selection("cyl8").label("Domain After Coil");
    model.component("comp1").selection("cyl8").set("r", "R_air*1.01");
    model.component("comp1").selection("cyl8").set("top", "L_pipe");
    model.component("comp1").selection("cyl8").set("bottom", "x_coil + L_coil");
    model.component("comp1").selection("cyl8").set("axistype", "cartesian");
    model.component("comp1").selection("cyl8").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").selection("cyl8").set("condition", "inside");
    model.component("comp1").selection().create("box2", "Box");
    model.component("comp1").selection("box2").label("Coil Start Edges (Reduced)");
    model.component("comp1").selection("box2").set("entitydim", 1);
    model.component("comp1").selection("box2").set("inputent", "selections");
    model.component("comp1").selection("box2").set("input", new String[]{"dif5"});
    model.component("comp1").selection("box2").set("xmin", "x_coil");
    model.component("comp1").selection("box2").set("xmax", "x_coil + L_coil");
    model.component("comp1").selection("box2").set("ymin", 0);
    model.component("comp1").selection("box2").set("zmin", 0);
    model.component("comp1").selection("box2").set("condition", "inside");
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("Domain - Fluid");
    model.component("comp1").selection("com1").set("input", new String[]{"geom1_csel2_dom"});
    model.component("comp1").selection().create("dif6", "Difference");
    model.component("comp1").selection("dif6").label("Pipes Inlet");
    model.component("comp1").selection("dif6").set("entitydim", 2);
    model.component("comp1").selection("dif6").set("add", new String[]{"int3"});
    model.component("comp1").selection("dif6").set("subtract", new String[]{"int1"});
    model.component("comp1").selection().duplicate("dif7", "dif6");
    model.component("comp1").selection("dif7").label("Pipes Outlet");
    model.component("comp1").selection("dif7").set("add", new String[]{"int4"});
    model.component("comp1").selection("dif7").set("subtract", new String[]{"int2"});
    model.component("comp1").selection().create("adj3", "Adjacent");
    model.component("comp1").selection("adj3").label("Pipes Inlet Interior Edges");
    model.component("comp1").selection("adj3").set("entitydim", 2);
    model.component("comp1").selection("adj3").set("input", new String[]{"dif6"});
    model.component("comp1").selection("adj3").set("outputdim", 1);
    model.component("comp1").selection("adj3").set("interior", true);
    model.component("comp1").selection().create("adj4", "Adjacent");
    model.component("comp1").selection("adj4").label("Fluid Inlet Edges");
    model.component("comp1").selection("adj4").set("entitydim", 2);
    model.component("comp1").selection("adj4").set("input", new String[]{"int1"});
    model.component("comp1").selection("adj4").set("outputdim", 1);
    model.component("comp1").selection().create("adj5", "Adjacent");
    model.component("comp1").selection("adj5").label("Reflection Plane Edges");
    model.component("comp1").selection("adj5").set("entitydim", 2);
    model.component("comp1").selection("adj5").set("input", new String[]{"geom1_csel5_bnd"});
    model.component("comp1").selection("adj5").set("outputdim", 1);
    model.component("comp1").selection().create("dif8", "Difference");
    model.component("comp1").selection("dif8").label("Fluid Inlet Edges Minus Reflection Plane");
    model.component("comp1").selection("dif8").set("entitydim", 1);
    model.component("comp1").selection("dif8").set("add", new String[]{"adj4"});
    model.component("comp1").selection("dif8").set("subtract", new String[]{"adj5"});
    model.component("comp1").selection().create("dif9", "Difference");
    model.component("comp1").selection("dif9").label("(Domain - Pipes) Inlet");
    model.component("comp1").selection("dif9").set("entitydim", 2);
    model.component("comp1").selection("dif9").set("add", new String[]{"cyl5"});
    model.component("comp1").selection("dif9").set("subtract", new String[]{"dif6"});
    model.component("comp1").selection().create("dif10", "Difference");
    model.component("comp1").selection("dif10").label("Fluid Lateral Walls");
    model.component("comp1").selection("dif10").set("entitydim", 2);
    model.component("comp1").selection("dif10").set("add", new String[]{"adj1"});
    model.component("comp1").selection("dif10").set("subtract", new String[]{"int1", "int2"});
    model.component("comp1").selection().create("dif11", "Difference");
    model.component("comp1").selection("dif11").label("Fluid Lateral Walls Minus Reflection Plane");
    model.component("comp1").selection("dif11").set("entitydim", 2);
    model.component("comp1").selection("dif11").set("add", new String[]{"dif10"});
    model.component("comp1").selection("dif11").set("subtract", new String[]{"geom1_csel5_bnd"});
    model.component("comp1").selection().create("int6", "Intersection");
    model.component("comp1").selection("int6").label("Intersection of Pipes Inlet and Reflection Plane Edges");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").selection("int6").set("entitydim", 1);
    model.component("comp1").selection("int6").set("input", new String[]{"adj3", "adj5"});

    model.component("comp1").physics("ht").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("ht").feature("fluid1").selection().named("geom1_csel2_dom");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().named("int1");
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_in");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("int2");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("dif3");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "5[W/(m^2*K)]");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().named("geom1_csel5_bnd");
    model.component("comp1").physics("spf").selection().named("geom1_csel2_dom");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("int1");
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "U_in");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("int2");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("geom1_csel5_bnd");
    model.component("comp1").physics("rad").selection().named("dif3");
    model.component("comp1").physics("rad").feature("dsurf1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("rad").feature("dsurf1").set("epsilon_rad", 0.9);
    model.component("comp1").physics("rad").create("rsym1", "SymmetryForSurfaceToSurfaceRadiation", -1);
    model.component("comp1").physics("rad").feature("rsym1").set("typeOfSymmetry", "SectorSymmetry");
    model.component("comp1").physics("rad").feature("rsym1").setIndex("numberOfSectors", 4, 0);
    model.component("comp1").physics("rad").feature("rsym1").set("isSectorWithReflection", true);
    model.component("comp1").physics("rad").feature("rsym1")
         .set("dReflection", new String[]{"0", "cos(pi/N_pipe)", "sin(pi/N_pipe)"});
    model.component("comp1").physics("mf").selection().named("com1");
    model.component("comp1").physics("mf").prop("EquationForm").setIndex("form", "Frequency", 0);
    model.component("comp1").physics("mf").prop("EquationForm").setIndex("freq_src", "userdef", 0);
    model.component("comp1").physics("mf").prop("EquationForm").setIndex("freq", "f_coil", 0);

    model.component("comp1").multiphysics().create("htrad1", "HeatTransferWithSurfaceToSurfaceRadiation", 2);
    model.component("comp1").multiphysics("htrad1").selection().named("dif3");
    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 3);
    model.component("comp1").multiphysics("emh1").selection().named("dif1");

    model.component("comp1").physics("mf").create("coil1", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil1").selection().named("int5");
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("CoilType", "Circular");
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "I_coil");
    model.component("comp1").physics("mf").feature("coil1").set("N", "N_coil");
    model.component("comp1").physics("mf").feature("coil1").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("mf").feature("coil1").set("HarmonicLoss", false);
    model.component("comp1").physics("mf").feature("coil1").set("coilWindArea", "a_coil");
    model.component("comp1").physics("mf").feature("coil1").feature("cre1").selection().named("box2");
    model.component("comp1").physics("mf").feature("coil1").feature("cre1").set("OverrideLength", true);
    model.component("comp1").physics("mf").feature("coil1").feature("cre1").set("length", "2*pi*R_coil/geom_coil");
    model.component("comp1").physics("mf").feature("coil1").feature("cre1").set("fl", 8);

    model.material().create("mat1", "Common", "");
    model.material("mat1").label("Milk");
    model.material("mat1").propertyGroup("def").set("density", "");
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", "");
    model.material("mat1").propertyGroup("def").set("heatcapacity", "");
    model.material("mat1").propertyGroup("def").set("thermalconductivity", "");
    model.material("mat1").propertyGroup("def").set("density", new String[]{"1030[kg/m^3]"});
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"2e-3[Pa*s]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"3900[J/(kg*K)]"});
    model.material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"0.53[W/(m*K)]"});
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat2").propertyGroup("def").func().create("rho", "Piecewise");
    model.material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat2").propertyGroup("def").func().create("cs", "Interpolation");
    model.material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.material("mat2").propertyGroup("def").func().create("an3", "Analytic");
    model.material("mat2").label("Water, liquid");
    model.material("mat2").set("family", "water");
    model.material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat2").propertyGroup("def").func("rho").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.material("mat2").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.material("mat2").propertyGroup("def").func("rho").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat2").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.material("mat2").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.material("mat2").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.material("mat2").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat2").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.material("mat2").propertyGroup("def").func("an2").set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.material("mat2").propertyGroup("def").func("an2").set("fununit", "1");
    model.material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("def").func("an2").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat2").propertyGroup("def").func("an3").set("funcname", "muB");
    model.material("mat2").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.material("mat2").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.material("mat2").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.material("mat2").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("def").func("an3").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat2").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat2").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat2").propertyGroup("def").set("density", "rho(T)");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.material("mat2").propertyGroup("def").addInput("temperature");
    model.material().duplicate("mat3", "mat1");
    model.material("mat3").set("sys", "none");
    model.material("mat3").label("Tomato Soup");
    model.material("mat3").propertyGroup("def").set("density", new String[]{"1020[kg/m^3]"});
    model.material("mat3").propertyGroup("def").set("dynamicviscosity", new String[]{"3e-3[Pa*s]"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", new String[]{"4000[J/(kg*K)]"});
    model.material("mat3").propertyGroup("def").set("thermalconductivity", new String[]{"0.6[W/(m*K)]"});
    model.material().create("mat4", "Common", "");
    model.material("mat4").label("Stainless Steel 410s");
    model.material("mat4").propertyGroup("def").set("density", "");
    model.material("mat4").propertyGroup("def").set("electricconductivity", "");
    model.material("mat4").propertyGroup("def").set("heatcapacity", "");
    model.material("mat4").propertyGroup("def").set("relpermeability", "");
    model.material("mat4").propertyGroup("def").set("relpermittivity", "");
    model.material("mat4").propertyGroup("def").set("thermalconductivity", "");
    model.material("mat4").propertyGroup("def").set("density", new String[]{"7.73e3[kg/m^3]"});
    model.material("mat4").propertyGroup("def").set("electricconductivity", new String[]{"sigma_410s"});
    model.material("mat4").propertyGroup("def").set("heatcapacity", new String[]{"460[J/(kg*K)]"});
    model.material("mat4").propertyGroup("def").set("relpermeability", new String[]{"mur_410s"});
    model.material("mat4").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.material("mat4").propertyGroup("def").set("thermalconductivity", new String[]{"26.9[W/(m*K)]"});
    model.material().duplicate("mat5", "mat4");
    model.material("mat5").set("sys", "none");
    model.material("mat5").label("Stainless Steel 410");
    model.material("mat5").propertyGroup("def").set("density", new String[]{"7.74e3[kg/m^3]"});
    model.material("mat5").propertyGroup("def").set("electricconductivity", new String[]{"sigma_410"});
    model.material("mat5").propertyGroup("def").set("heatcapacity", new String[]{"460[J/(kg*K)]"});
    model.material("mat5").propertyGroup("def").set("relpermeability", new String[]{"mur_410"});
    model.material("mat5").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.material("mat5").propertyGroup("def").set("thermalconductivity", new String[]{"24.9[W/(m*K)]"});
    model.material().duplicate("mat6", "mat4");
    model.material("mat6").set("sys", "none");
    model.material("mat6").label("Stainless Steel User Defined");
    model.material("mat6").propertyGroup("def").set("density", new String[]{"7.73e3[kg/m^3]"});
    model.material("mat6").propertyGroup("def").set("electricconductivity", new String[]{"sigma_ud"});
    model.material("mat6").propertyGroup("def").set("heatcapacity", new String[]{"460[J/(kg*K)]"});
    model.material("mat6").propertyGroup("def").set("relpermeability", new String[]{"mur_ud"});
    model.material("mat6").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.material("mat6").propertyGroup("def").set("thermalconductivity", new String[]{"26.9[W/(m*K)]"});
    model.component("comp1").material().create("mat7", "Common");
    model.component("comp1").material("mat7").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat7").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat7").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat7").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat7").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat7").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat7").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat7").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat7").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat7").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat7").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat7").label("Air");
    model.component("comp1").material("mat7").set("family", "air");
    model.component("comp1").material("mat7").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat7").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat7").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat7").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat7").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat7").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat7").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat7").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat7").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat7").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat7").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat7").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat7").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat7").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat7").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat7").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat7").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat7").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat7").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat7").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat7").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat7").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat7").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat7").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat7").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat7").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat7").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat7").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat7").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat7").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat7").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat7").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat7").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat7").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat7").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat7").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat7").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat7").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat7").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat7").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat7").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat7").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat7").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat7").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat7").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat7").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat7").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat7").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat7").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat7").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat7").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat7").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat7").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat7").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat7").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat7").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat7").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat7").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat7").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat7").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat7").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat7").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat7").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat7").materialType("nonSolid");
    model.component("comp1").material("mat7").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat7").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"eta(T[1/K])[Pa*s]"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.4[S/m]"});
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").label("Material Link Fluid");
    model.component("comp1").material("matlnk1").selection().named("geom1_csel2_dom");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").label("Material Link Pipes");
    model.component("comp1").material("matlnk2").selection().named("dif1");
    model.component("comp1").material("matlnk2").set("link", "mat4");

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature().remove("size1");
    model.component("comp1").mesh("mesh1").feature().remove("size2");
    model.component("comp1").mesh("mesh1").feature().remove("size3");
    model.component("comp1").mesh("mesh1").feature().remove("cr1");
    model.component("comp1").mesh("mesh1").feature().remove("ftet1");
    model.component("comp1").mesh("mesh1").feature().remove("bl1");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("dif6");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().named("dif8");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", "floor(10/sqrt(N_pipe))");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().named("int6");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", "floor(t_pipe/sd_pipe)+1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("dif9");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hnarrow", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().named("int1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "r_i_pipe/5");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hnarrow", 2);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("int1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("dif8");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", 4);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().named("cyl6");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", "floor(50*x_coil)");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().named("cyl7");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", "floor(80*L_coil)");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").selection().named("cyl8");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3")
         .set("elemcount", "floor(40*(L_pipe-x_coil-L_coil))");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("plist", "f_coil");
    model.study("std1").feature("freq").set("usestol", true);
    model.study("std1").feature("freq").set("stol", "stol");
    model.study("std1").create("wdi", "WallDistanceInitialization");
    model.study("std1").feature("wdi").setSolveFor("/physics/mf", false);
    model.study("std1").feature("wdi").setSolveFor("/multiphysics/emh1", false);

    return model;
  }

  public static Model run3(Model model) {
    model.study("std1").feature("wdi").set("usestol", true);
    model.study("std1").feature("wdi").set("stol", "stol");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", false);
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "stol");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Temperature (ht)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Velocity (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("slc1", "Slice");
    model.result("pg2").feature("slc1").label("Slice");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("expr", "spf.U");
    model.result("pg2").feature("slc1").set("smooth", "internal");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("Exterior Walls");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(9, 11, 32, 34, 55, 57);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("Pressure (spf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "p");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("Wall Resolution (spf)");
    model.result("pg4").set("data", "surf1");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("Wall Resolution");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("Surface Radiosity (rad)");
    model.result("pg5").feature().create("slit1", "SurfaceSlit");
    model.result("pg5").feature("slit1").set("showsolutionparams", "on");
    model.result("pg5").feature("slit1").set("upexpr", "rad.Ju");
    model.result("pg5").feature("slit1").set("downexpr", "rad.Jd");
    model.result("pg5").feature("slit1").set("smooth", "internal");
    model.result("pg5").feature("slit1").set("showsolutionparams", "on");
    model.result("pg5").feature("slit1").set("data", "parent");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("Magnetic Flux Density (mf)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("solutionparams", "parent");
    model.result("pg6").feature("mslc1").set("expr", "mf.normB");
    model.result("pg6").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("mslc1").set("xcoord", "mf.CPx");
    model.result("pg6").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("mslc1").set("ycoord", "mf.CPy");
    model.result("pg6").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("mslc1").set("zcoord", "mf.CPz");
    model.result("pg6").feature("mslc1").set("colortable", "Prism");
    model.result("pg6").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result("pg6").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg6").feature("strmsl1").set("expr", new String[]{"mf.Bx", "mf.By", "mf.Bz"});
    model.result("pg6").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("strmsl1").set("xcoord", "mf.CPx");
    model.result("pg6").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("strmsl1").set("ycoord", "mf.CPy");
    model.result("pg6").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("strmsl1").set("zcoord", "mf.CPz");
    model.result("pg6").feature("strmsl1").set("titletype", "none");
    model.result("pg6").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg6").feature("strmsl1").set("udist", 0.02);
    model.result("pg6").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg6").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("inheritcolor", false);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("data", "parent");
    model.result("pg6").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg6").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg6").feature("strmsl1").feature("col1").set("expr", "mf.normB");
    model.result("pg6").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg6").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg6").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg6").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("Temperature and Fluid Flow (nitf1)");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("Wall Temperature");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("solutionparams", "parent");
    model.result("pg7").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg7").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg7").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg7").feature("surf1").feature("sel1").selection().set(9, 11, 32, 34, 55, 57);
    model.result("pg7").feature().create("vol1", "Volume");
    model.result("pg7").feature("vol1").label("Solid Temperature");
    model.result("pg7").feature("vol1").set("showsolutionparams", "on");
    model.result("pg7").feature("vol1").set("solutionparams", "parent");
    model.result("pg7").feature("vol1").set("expr", "nitf1.T");
    model.result("pg7").feature("vol1").set("smooth", "internal");
    model.result("pg7").feature("vol1").set("showsolutionparams", "on");
    model.result("pg7").feature("vol1").set("data", "parent");
    model.result("pg7").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg7").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg7").feature("vol1").feature("sel1").selection().set(2, 7, 12);
    model.result("pg7").feature("vol1").set("inheritplot", "surf1");
    model.result("pg7").feature().create("arwv1", "ArrowVolume");
    model.result("pg7").feature("arwv1").label("Fluid Flow");
    model.result("pg7").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg7").feature("arwv1").set("solutionparams", "parent");
    model.result("pg7").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg7").feature("arwv1").set("xnumber", 30);
    model.result("pg7").feature("arwv1").set("ynumber", 30);
    model.result("pg7").feature("arwv1").set("znumber", 30);
    model.result("pg7").feature("arwv1").set("arrowtype", "cone");
    model.result("pg7").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg7").feature("arwv1").set("data", "parent");
    model.result("pg7").feature("arwv1").feature().create("col1", "Color");
    model.result("pg7").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg7").feature("arwv1").feature("col1").set("expr", "spf.U");
    model.result("pg7").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg7").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result().dataset("surf1").selection().named("dif11");
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").label("Central Line Along the Tubes");
    model.result().dataset("cln1").setIndex("genpoints", "L_pipe", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "if(N_pipe == 1, 0, x_i_pipe)", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "if(N_pipe == 1, 0, x_i_pipe)", 1, 1);
    model.result().dataset("cln1").setIndex("genpoints", "if(N_pipe == 1, 0, y_i_pipe)", 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", "if(N_pipe == 1, 0, y_i_pipe)", 1, 2);
    model.result().dataset("cln1").set("snapping", "boundary");
    model.result().dataset().create("cln2", "CutLine3D");
    model.result().dataset("cln2").label("Interior Wall Line Along the Tubes");
    model.result().dataset("cln2").setIndex("genpoints", "L_pipe", 1, 0);
    model.result().dataset("cln2")
         .setIndex("genpoints", "if(N_pipe == 1, r_pipe, x_i_pipe+r_i_pipe*cos(pi/N_pipe))", 0, 1);
    model.result().dataset("cln2")
         .setIndex("genpoints", "if(N_pipe == 1, r_pipe, x_i_pipe+r_i_pipe*cos(pi/N_pipe))", 1, 1);
    model.result().dataset("cln2")
         .setIndex("genpoints", "if(N_pipe == 1, 0, y_i_pipe+r_i_pipe*sin(pi/N_pipe))", 0, 2);
    model.result().dataset("cln2")
         .setIndex("genpoints", "if(N_pipe == 1, 0, y_i_pipe+r_i_pipe*sin(pi/N_pipe))", 1, 2);
    model.result().dataset("cln2").set("snapping", "boundary");
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").label("Mirror of Domain");
    model.result().dataset("mir1").set("planetype", "general");
    model.result().dataset("mir1").setIndex("genpoints", "L_pipe", 1, 0);
    model.result().dataset("mir1").setIndex("genpoints", "L_pipe", 2, 0);
    model.result().dataset("mir1").setIndex("genpoints", "R_air*cos(2*pi/N_pipe/2)", 2, 1);
    model.result().dataset("mir1").setIndex("genpoints", "R_air*sin(2*pi/N_pipe/2)", 2, 2);
    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").label("Revolution of Domain");
    model.result().dataset("sec1").set("data", "mir1");
    model.result().dataset("sec1").setIndex("genpoints", "L_pipe", 1, 0);
    model.result().dataset("sec1").setIndex("genpoints", 0, 1, 2);
    model.result().dataset("sec1").set("sectors", "max(N_pipe,2)");
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").label("x Cut Plane");
    model.result().dataset("cpl1").set("quickx", "x_coil+L_coil/2");
    model.result().dataset().create("sec2", "Sector2D");
    model.result().dataset("sec2").label("Revolution of x Cut Plane");
    model.result().dataset("sec2").set("data", "cpl1");
    model.result().dataset("sec2").set("sectors", "N_pipe*2");
    model.result().dataset().create("dset4", "Solution");
    model.result().dataset("dset4").label("Study 1/Solution 1 in Tubes");
    model.result().dataset("dset4").selection().geom("geom1", 3);
    model.result().dataset("dset4").selection().named("geom1_csel1_dom");
    model.result().dataset().create("cpl2", "CutPlane");
    model.result().dataset("cpl2").label("Tubes, x Cut Plane");
    model.result().dataset("cpl2").set("data", "dset4");
    model.result().dataset("cpl2").set("quickx", "x_coil + L_coil/2");
    model.result().dataset().create("dset5", "Solution");
    model.result().dataset("dset5").label("Study 1/Solution 1 in Coil");
    model.result().dataset("dset5").selection().geom("geom1", 3);
    model.result().dataset("dset5").selection().named("int5");
    model.result().dataset().create("sec3", "Sector3D");
    model.result().dataset("sec3").label("Revolution of Coil");
    model.result().dataset("sec3").set("data", "dset5");
    model.result().dataset("sec3").setIndex("genpoints", "L_pipe", 1, 0);
    model.result().dataset("sec3").setIndex("genpoints", 0, 1, 2);
    model.result().dataset("sec3").set("sectors", "N_pipe*2");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("Global Evaluation: Thermal Efficiency");
    model.result().numerical("gev1").setIndex("expr", "2*ht.QInt/mf.PCoil_1*100", 0);
    model.result().numerical("gev1").setIndex("unit", 1, 0);
    model.result().numerical("gev1").setIndex("descr", "", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Global Evaluation: Thermal Efficiency");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("max1", "MaxVolume");
    model.result().numerical("max1").label("Max Temperature in Fluid");
    model.result().numerical("max1").selection().named("geom1_csel2_dom");
    model.result().numerical("max1").setIndex("expr", "T", 0);
    model.result().numerical("max1").setIndex("unit", "\u00b0C", 0);
    model.result().numerical("max1").setIndex("descr", "Temperature", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Max Temperature in Fluid");
    model.result().numerical("max1").set("table", "tbl2");
    model.result().numerical("max1").setResult();
    model.result().numerical().create("av1", "AvSurface");
    model.result().numerical("av1").set("intvolume", true);
    model.result().numerical("av1").label("Average Temperature Elevation at Pipes Outlet");
    model.result().numerical("av1").selection().named("int2");
    model.result().numerical("av1").setIndex("expr", "T-(T_in-273.15)", 0);
    model.result().numerical("av1").setIndex("unit", "\u00b0C", 0);
    model.result().numerical("av1").setIndex("descr", "", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("Average Temperature Elevation at Pipes Outlet");
    model.result().numerical("av1").set("table", "tbl3");
    model.result().numerical("av1").setResult();
    model.result().numerical().create("min1", "MinSurface");
    model.result().numerical("min1").label("Min Temperature at Pipes Outlet");
    model.result().numerical("min1").selection().named("int2");
    model.result().numerical("min1").setIndex("expr", "T", 0);
    model.result().numerical("min1").setIndex("unit", "\u00b0C", 0);
    model.result().numerical("min1").setIndex("descr", "Temperature", 0);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("Min Temperature at Pipes Outlet");
    model.result().numerical("min1").set("table", "tbl4");
    model.result().numerical("min1").setResult();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("Temperature over x");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "Center (blue) and interior wall (green)");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").set("data", "cln1");
    model.result("pg8").feature("lngr1").set("unit", "\u00b0C");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "x");
    model.result("pg8").feature().duplicate("lngr2", "lngr1");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("data", "cln2");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").run();
    model.result("pg9").label("Temperature at x Cut Plane");
    model.result("pg9").set("data", "cpl2");
    model.result("pg9").set("titletype", "none");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("unit", "\u00b0C");
    model.result("pg9").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").run();
    model.result("pg10").label("Fluid flow at x Cut Plane");
    model.result("pg10").set("data", "cpl2");
    model.result("pg10").set("titletype", "none");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "spf.U");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").run();
    model.result("pg11").label("Magnetic Flux at x Cut Plane");
    model.result("pg11").set("data", "cpl1");
    model.result("pg11").set("edges", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "mf.normB");
    model.result("pg11").feature("surf1").set("rangecoloractive", true);
    model.result("pg11").feature("surf1").set("rangecolormin", 0.0155);
    model.result("pg11").feature("surf1").set("rangecolormax", 0.1);
    model.result("pg11").feature("surf1").set("titletype", "none");
    model.result("pg11").run();
    model.result("pg11").create("con1", "Contour");
    model.result("pg11").feature("con1").set("expr", "sqrt(y^2 + z^2)");
    model.result("pg11").feature("con1").set("titletype", "none");
    model.result("pg11").feature("con1").set("levelmethod", "levels");
    model.result("pg11").feature("con1").set("levels", "range(R_coil-R_wire,2*R_wire,R_coil+R_wire)");
    model.result("pg11").feature("con1").set("coloring", "uniform");
    model.result("pg11").feature("con1").set("color", "white");
    model.result("pg11").feature("con1").set("colorlegend", false);
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").run();
    model.result("pg12").label("Full Solution");
    model.result("pg12").set("data", "sec1");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("unit", "\u00b0C");
    model.result("pg12").feature("surf1").set("titletype", "none");
    model.result("pg12").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg12").run();
    model.result("pg12").create("str1", "Streamline");
    model.result("pg12").feature("str1").setIndex("expr", "mf.Bx", 0);
    model.result("pg12").feature("str1").setIndex("expr", "mf.By", 1);
    model.result("pg12").feature("str1").set("expr", new String[]{"mf.Bx", "mf.By", "mf.Bz"});
    model.result("pg12").feature("str1").set("color", "fromtheme");
    model.result("pg12").feature("str1").set("titletype", "none");

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg12").run();
    model.result("pg12").create("slc1", "Slice");
    model.result("pg12").feature("slc1").set("expr", "mf.normB");
    model.result("pg12").feature("slc1").set("quickxnumber", 1);
    model.result("pg12").feature("slc1").set("interactive", true);
    model.result("pg12").feature("slc1").set("titletype", "none");
    model.result("pg12").feature("slc1").set("rangecoloractive", true);
    model.result("pg12").feature("slc1").set("rangecolormin", 0.02);
    model.result("pg12").feature("slc1").set("rangecolormax", 0.1);
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").run();
    model.result("pg13").label("Full Geometry");
    model.result("pg13").set("data", "sec1");
    model.result("pg13").set("titletype", "none");
    model.result("pg13").set("edges", false);
    model.result("pg13").set("showlegends", false);
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", "ht.rho");
    model.result("pg13").feature("surf1").set("titletype", "none");
    model.result("pg13").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg13").run();
    model.result("pg13").create("surf2", "Surface");
    model.result("pg13").feature("surf2").set("data", "sec3");
    model.result("pg13").feature("surf2").set("expr", "mf.PCoil_1");
    model.result("pg13").feature("surf2").set("descr", "Coil power");
    model.result("pg13").run();
    model.result("pg12").run();

    model.title("\u7ba1\u7ebf\u611f\u5e94\u52a0\u70ed\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u4f7f\u7528\u5bf9\u79f0\u7ed3\u6784\u7684\u6a21\u578b\uff0c\u7ed3\u679c\u4ee5\u5168\u4e09\u7ef4\u7684\u5f62\u5f0f\u53ef\u89c6\u5316\n\u2022 \u5982\u679c\u7ed3\u679c\u5927\u4e8e\u6216\u5c0f\u4e8e\u67d0\u4e9b\u4e34\u754c\u503c\uff0c\u5219\u63d0\u4f9b\u76f8\u5173\u4fe1\u606f\n\u2022 \u9009\u62e9\u9884\u5b9a\u4e49\u6216\u7528\u6237\u5b9a\u4e49\u7684\u6750\u6599\n\u2022 \u4f7f\u7528\u65b9\u6cd5\u5bf9\u51e0\u4f55\u53c2\u6570\u8fdb\u884c\u8bef\u5dee\u63a7\u5236\uff0c\u5e76\u4f7f\u7528\u5361\u7247\u5806\u6f14\u793a\u53ef\u80fd\u7684\u9519\u8bef\n\u2022 \u5728\u901a\u8fc7\u5207\u9762\u56fe\u53ef\u89c6\u5316\u7ed3\u679c\u65f6\uff0c\u4f7f\u7528\u6ed1\u5757\u548c\u6309\u94ae\u6765\u63a7\u5236\u5207\u9762\u7684\u4f4d\u7f6e\n\n\u8be5 App \u8ba1\u7b97\u78c1\u611f\u5e94\u5668\u4e3a\u4e00\u7ec4\u94c1\u7d20\u4f53\u4e0d\u9508\u94a2\u7ba1\u4e2d\u7684\u6d41\u8d28\u98df\u7269\u52a0\u70ed\u7684\u6548\u7387\u3002\n\n\u94c1\u7d20\u4f53\u4e0d\u9508\u94a2\u4e0d\u4ec5\u4ef7\u683c\u76f8\u5bf9\u4f4e\u5ec9\u7a33\u5b9a\uff0c\u5176\u78c1\u5c5e\u6027\u8fd8\u652f\u6301\u4f7f\u7528\u65b0\u7684\u52a0\u70ed\u6280\u672f\uff0c\u56e0\u6b64\u5728\u98df\u54c1\u52a0\u5de5\u884c\u4e1a\u7684\u5e94\u7528\u8d8a\u6765\u8d8a\u5e7f\u6cdb\u3002\n\n\u4e00\u6839\u5706\u5f62\u7535\u78c1\u7ebf\u5708\u7f20\u7ed5\u5728\u4e00\u7ec4\u5177\u6709\u6d41\u4f53\u6d41\u52a8\u7684\u7ba1\u9053\u5468\u56f4\u3002\u901a\u8fc7\u7ebf\u5708\u7684\u4ea4\u6d41\u7535\u4f1a\u4ea7\u751f\u53ef\u7a7f\u900f\u7ba1\u9053\u7684\u4ea4\u53d8\u78c1\u573a\uff0c\u4ece\u800c\u5728\u7ba1\u9053\u5185\u4ea7\u751f\u6da1\u6d41\uff0c\u4f7f\u5176\u5347\u6e29\u3002\u7136\u540e\uff0c\u70ed\u91cf\u4f1a\u901a\u8fc7\u4f20\u5bfc\u65b9\u5f0f\u4f20\u9012\u5230\u6d41\u4f53\u3002\n\n\u672c\u4f8b\u4e3a\u8fd9\u7ec4\u7ba1\u9053\uff08\u6570\u91cf\u3001\u957f\u5ea6\u3001\u539a\u5ea6\u3001\u6750\u6599\uff09\u548c\u7ebf\u5708\uff08\u531d\u6570\u3001\u5bfc\u7ebf\u534a\u5f84\u3001\u7535\u6d41\u5bc6\u5ea6\u548c\u6fc0\u53d1\u9891\u7387\uff09\u63d0\u4f9b\u5404\u79cd\u6784\u578b\uff0c\u4ee5\u4f18\u5316\u4e0e\u6d41\u4f53\u7684\u70ed\u4ea4\u6362\uff0c\u540c\u65f6\u786e\u4fdd\u7ba1\u5185\u6e29\u5ea6\u5728\u7ed9\u5b9a\u6d41\u7387\u4e0b\u4fdd\u6301\u5747\u5300\u3002");

    model.label("inline_induction_heater.mph");

    model.result("pg12").run();

    model.title("\u7ba1\u7ebf\u611f\u5e94\u52a0\u70ed\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u4f7f\u7528\u5bf9\u79f0\u7ed3\u6784\u7684\u6a21\u578b\uff0c\u7ed3\u679c\u4ee5\u5168\u4e09\u7ef4\u7684\u5f62\u5f0f\u53ef\u89c6\u5316\n\u2022 \u5982\u679c\u7ed3\u679c\u5927\u4e8e\u6216\u5c0f\u4e8e\u67d0\u4e9b\u4e34\u754c\u503c\uff0c\u5219\u63d0\u4f9b\u76f8\u5173\u4fe1\u606f\n\u2022 \u9009\u62e9\u9884\u5b9a\u4e49\u6216\u7528\u6237\u5b9a\u4e49\u7684\u6750\u6599\n\u2022 \u4f7f\u7528\u65b9\u6cd5\u5bf9\u51e0\u4f55\u53c2\u6570\u8fdb\u884c\u8bef\u5dee\u63a7\u5236\uff0c\u5e76\u4f7f\u7528\u5361\u7247\u5806\u6f14\u793a\u53ef\u80fd\u7684\u9519\u8bef\n\u2022 \u5728\u901a\u8fc7\u5207\u9762\u56fe\u53ef\u89c6\u5316\u7ed3\u679c\u65f6\uff0c\u4f7f\u7528\u6ed1\u5757\u548c\u6309\u94ae\u6765\u63a7\u5236\u5207\u9762\u7684\u4f4d\u7f6e\n\n\u8be5 App \u8ba1\u7b97\u78c1\u611f\u5e94\u5668\u4e3a\u4e00\u7ec4\u94c1\u7d20\u4f53\u4e0d\u9508\u94a2\u7ba1\u4e2d\u7684\u6d41\u8d28\u98df\u7269\u52a0\u70ed\u7684\u6548\u7387\u3002\n\n\u94c1\u7d20\u4f53\u4e0d\u9508\u94a2\u4e0d\u4ec5\u4ef7\u683c\u76f8\u5bf9\u4f4e\u5ec9\u7a33\u5b9a\uff0c\u5176\u78c1\u5c5e\u6027\u8fd8\u652f\u6301\u4f7f\u7528\u65b0\u7684\u52a0\u70ed\u6280\u672f\uff0c\u56e0\u6b64\u5728\u98df\u54c1\u52a0\u5de5\u884c\u4e1a\u7684\u5e94\u7528\u8d8a\u6765\u8d8a\u5e7f\u6cdb\u3002\n\n\u4e00\u6839\u5706\u5f62\u7535\u78c1\u7ebf\u5708\u7f20\u7ed5\u5728\u4e00\u7ec4\u5177\u6709\u6d41\u4f53\u6d41\u52a8\u7684\u7ba1\u9053\u5468\u56f4\u3002\u901a\u8fc7\u7ebf\u5708\u7684\u4ea4\u6d41\u7535\u4f1a\u4ea7\u751f\u53ef\u7a7f\u900f\u7ba1\u9053\u7684\u4ea4\u53d8\u78c1\u573a\uff0c\u4ece\u800c\u5728\u7ba1\u9053\u5185\u4ea7\u751f\u6da1\u6d41\uff0c\u4f7f\u5176\u5347\u6e29\u3002\u7136\u540e\uff0c\u70ed\u91cf\u4f1a\u901a\u8fc7\u4f20\u5bfc\u65b9\u5f0f\u4f20\u9012\u5230\u6d41\u4f53\u3002\n\n\u672c\u4f8b\u4e3a\u8fd9\u7ec4\u7ba1\u9053\uff08\u6570\u91cf\u3001\u957f\u5ea6\u3001\u539a\u5ea6\u3001\u6750\u6599\uff09\u548c\u7ebf\u5708\uff08\u531d\u6570\u3001\u5bfc\u7ebf\u534a\u5f84\u3001\u7535\u6d41\u5bc6\u5ea6\u548c\u6fc0\u53d1\u9891\u7387\uff09\u63d0\u4f9b\u5404\u79cd\u6784\u578b\uff0c\u4ee5\u4f18\u5316\u4e0e\u6d41\u4f53\u7684\u70ed\u4ea4\u6362\uff0c\u540c\u65f6\u786e\u4fdd\u7ba1\u5185\u6e29\u5ea6\u5728\u7ed9\u5b9a\u6d41\u7387\u4e0b\u4fdd\u6301\u5747\u5300\u3002");

    model.setExpectedComputationTime("2 \u5206\u949f");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///inline_induction_heater.docx");
    model.result().report("rpt1").set("imagesize", "large");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("frontmatterlayout", "headings");
    model.result().report("rpt1").feature("tp1").set("logo", "none");
    model.result().report("rpt1").feature("tp1").set("includeacknowledgment", false);
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature("toc1").label("\u76ee\u5f55");
    model.result().report("rpt1").feature("toc1").set("levels", "1");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("comp1", "ModelNode");
    model.result().report("rpt1").feature("sec1").feature("comp1").label("\u8f6f\u4ef6\u5c5e\u6027");
    model.result().report("rpt1").feature("sec1").feature("comp1").set("noderef", "none");
    model.result().report("rpt1").feature("sec1").feature("comp1").set("includeunitsystem", false);
    model.result().report("rpt1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u8f93\u5165\u6570\u636e");
    model.result().report("rpt1").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").label("\u7ba1\u548c\u7ebf\u5708\u51e0\u4f55");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 9, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 13, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 14, 1);

    return model;
  }

  public static Model run4(Model model) {
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 15, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 16, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 17, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 18, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 19, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 20, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 21, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 22, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 23, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 24, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 25, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 26, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 27, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 28, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 29, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 30, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 31, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 32, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 33, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 34, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 35, 1);
    model.result().report("rpt1").feature("sec2").feature().duplicate("sec2", "sec1");
    model.result().report("rpt1").feature("sec2").feature("sec2").label("\u7ba1\u6750");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("param1")
         .setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("param1")
         .setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("param1")
         .setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("param1")
         .setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("param1")
         .setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("param1")
         .setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("param1")
         .setIndex("children", true, 24, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("param1")
         .setIndex("children", true, 25, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("param1")
         .setIndex("children", true, 26, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("param1")
         .setIndex("children", true, 27, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("param1")
         .setIndex("children", true, 28, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("param1")
         .setIndex("children", true, 29, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().create("mat1", "Material");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("mat1").set("noderef", "matlnk2");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("mat1").set("includeimage", false);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("mat1").set("includeselection", false);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("mat1").set("includesettings", false);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("mat1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature().duplicate("sec3", "sec1");
    model.result().report("rpt1").feature("sec2").feature("sec3").label("\u7ebf\u5708\u6fc0\u6d3b");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1")
         .setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1")
         .setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1")
         .setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1")
         .setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1")
         .setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1")
         .setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1")
         .setIndex("children", true, 14, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1")
         .setIndex("children", true, 15, 1);
    model.result().report("rpt1").feature("sec2").feature().duplicate("sec4", "sec2");
    model.result().report("rpt1").feature("sec2").feature("sec4").label("\u6d41\u52a8\u6761\u4ef6");
    model.result().report("rpt1").feature("sec2").feature("sec4").feature("mat1").set("noderef", "matlnk1");
    model.result().report("rpt1").feature("sec2").feature("sec4").feature("param1")
         .setIndex("children", true, 20, 1);
    model.result().report("rpt1").feature("sec2").feature("sec4").feature("param1")
         .setIndex("children", true, 23, 1);
    model.result().report("rpt1").feature("sec2").feature("sec4").feature("param1")
         .setIndex("children", false, 24, 1);
    model.result().report("rpt1").feature("sec2").feature("sec4").feature("param1")
         .setIndex("children", false, 25, 1);
    model.result().report("rpt1").feature("sec2").feature("sec4").feature("param1")
         .setIndex("children", false, 26, 1);
    model.result().report("rpt1").feature("sec2").feature("sec4").feature("param1")
         .setIndex("children", false, 27, 1);
    model.result().report("rpt1").feature("sec2").feature("sec4").feature("param1")
         .setIndex("children", false, 28, 1);
    model.result().report("rpt1").feature("sec2").feature("sec4").feature("param1")
         .setIndex("children", false, 29, 1);
    model.result().report("rpt1").feature("sec2").feature().duplicate("sec5", "sec1");
    model.result().report("rpt1").feature("sec2").feature("sec5").label("\u76ee\u6807\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec2").feature("sec5").feature("param1")
         .setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("sec5").feature("param1")
         .setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec2").feature("sec5").feature("param1")
         .setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec2").feature("sec5").feature("param1")
         .setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec2").feature("sec5").feature("param1")
         .setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec2").feature("sec5").feature("param1")
         .setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec2").feature("sec5").feature("param1")
         .setIndex("children", true, 34, 1);
    model.result().report("rpt1").feature("sec2").feature("sec5").feature("param1")
         .setIndex("children", true, 35, 1);
    model.result().report("rpt1").feature("sec2").feature().duplicate("sec6", "sec1");
    model.result().report("rpt1").feature("sec2").feature("sec6").label("\u8ba1\u7b97");
    model.result().report("rpt1").feature("sec2").feature("sec6").feature("param1")
         .setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("sec6").feature("param1")
         .setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec2").feature("sec6").feature("param1")
         .setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec2").feature("sec6").feature("param1")
         .setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec2").feature("sec6").feature("param1")
         .setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec2").feature("sec6").feature("param1")
         .setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec2").feature("sec6").feature("param1")
         .setIndex("children", true, 33, 1);
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ec4\u4ef6");
    model.result().report("rpt1").feature("sec3").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec3").feature("geom1").label("\u51e0\u4f55");
    model.result().report("rpt1").feature("sec3").feature("geom1").set("includeunits", false);
    model.result().report("rpt1").feature("sec3").feature("geom1").set("includestats", false);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 9, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec3").feature().create("mesh1", "Mesh");
    model.result().report("rpt1").feature("sec3").feature("mesh1").label("\u7f51\u683c");
    model.result().report("rpt1").feature("sec3").feature("mesh1").set("includestats", true);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec4").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg1").label("\u7ba1\u914d\u7f6e");
    model.result().report("rpt1").feature("sec4").feature("pg1").set("noderef", "pg13");
    model.result().report("rpt1").feature("sec4").feature("pg1").set("caption", "custom");
    model.result().report("rpt1").feature("sec4").feature("pg1").set("customcaption", "\u7ba1\u914d\u7f6e");
    model.result().report("rpt1").feature("sec4").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 9, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 13, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 15, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 16, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 17, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 19, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 20, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 21, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 22, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 23, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 24, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 25, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 26, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 27, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 28, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 29, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 30, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 31, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 33, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 34, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 35, 1);
    model.result().report("rpt1").feature("sec4").feature().create("pg2", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg2").label("\u78c1\u901a\u91cf\uff0c\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec4").feature("pg2").set("noderef", "pg12");
    model.result().report("rpt1").feature("sec4").feature("pg2").set("caption", "custom");
    model.result().report("rpt1").feature("sec4").feature("pg2")
         .set("customcaption", "\u78c1\u901a\u91cf\uff0c\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec4").feature().create("pg3", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg3").label("\u622a\u9762\u78c1\u901a\u91cf");
    model.result().report("rpt1").feature("sec4").feature("pg3").set("noderef", "pg11");
    model.result().report("rpt1").feature("sec4").feature("pg3").set("caption", "custom");
    model.result().report("rpt1").feature("sec4").feature("pg3")
         .set("customcaption", "\u622a\u9762\u78c1\u901a\u91cf");
    model.result().report("rpt1").feature("sec4").feature().create("pg4", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg4").label("\u6cbf\u7ba1\u7684\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec4").feature("pg4").set("noderef", "pg8");
    model.result().report("rpt1").feature("sec4").feature("pg4").set("caption", "custom");
    model.result().report("rpt1").feature("sec4").feature("pg4")
         .set("customcaption", "\u6cbf\u7ba1\u7684\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec4").feature().create("pg5", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg5").label("\u622a\u9762\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec4").feature("pg5").set("noderef", "pg9");
    model.result().report("rpt1").feature("sec4").feature("pg5").set("caption", "custom");
    model.result().report("rpt1").feature("sec4").feature("pg5").set("customcaption", "\u622a\u9762\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec4").feature().create("pg6", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg6")
         .label("\u622a\u9762\u4e0a\u7684\u6d41\u4f53\u901f\u5ea6");
    model.result().report("rpt1").feature("sec4").feature("pg6").set("noderef", "pg10");
    model.result().report("rpt1").feature("sec4").feature("pg6").set("caption", "custom");
    model.result().report("rpt1").feature("sec4").feature("pg6").set("customcaption", "\u622a\u9762\u6d41\u901f");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl1")
         .label("\u8868\u683c\uff1a\u6d41\u4f53\u4e2d\u7684\u6700\u9ad8\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec4").feature("mtbl1").set("noderef", "tbl2");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl2", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl2")
         .label("\u8868\u683c\uff1a\u51fa\u53e3\u7684\u6700\u4f4e\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec4").feature("mtbl2").set("noderef", "tbl4");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl3", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl3")
         .label("\u8868\u683c\uff1a\u51fa\u53e3\u7684\u6e29\u5347\u5e73\u5747\u503c");
    model.result().report("rpt1").feature("sec4").feature("mtbl3").set("noderef", "tbl3");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl4", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl4").label("\u8868\u683c\uff1a\u70ed\u6548\u7387");

    model.title("\u7ba1\u7ebf\u611f\u5e94\u52a0\u70ed\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u4f7f\u7528\u5bf9\u79f0\u7ed3\u6784\u7684\u6a21\u578b\uff0c\u7ed3\u679c\u4ee5\u5168\u4e09\u7ef4\u7684\u5f62\u5f0f\u53ef\u89c6\u5316\n\u2022 \u5982\u679c\u7ed3\u679c\u5927\u4e8e\u6216\u5c0f\u4e8e\u67d0\u4e9b\u4e34\u754c\u503c\uff0c\u5219\u63d0\u4f9b\u76f8\u5173\u4fe1\u606f\n\u2022 \u9009\u62e9\u9884\u5b9a\u4e49\u6216\u7528\u6237\u5b9a\u4e49\u7684\u6750\u6599\n\u2022 \u4f7f\u7528\u65b9\u6cd5\u5bf9\u51e0\u4f55\u53c2\u6570\u8fdb\u884c\u8bef\u5dee\u63a7\u5236\uff0c\u5e76\u4f7f\u7528\u5361\u7247\u5806\u6f14\u793a\u53ef\u80fd\u7684\u9519\u8bef\n\u2022 \u5728\u901a\u8fc7\u5207\u9762\u56fe\u53ef\u89c6\u5316\u7ed3\u679c\u65f6\uff0c\u4f7f\u7528\u6ed1\u5757\u548c\u6309\u94ae\u6765\u63a7\u5236\u5207\u9762\u7684\u4f4d\u7f6e\n\n\u8be5 App \u8ba1\u7b97\u78c1\u611f\u5e94\u5668\u4e3a\u4e00\u7ec4\u94c1\u7d20\u4f53\u4e0d\u9508\u94a2\u7ba1\u4e2d\u7684\u6d41\u8d28\u98df\u7269\u52a0\u70ed\u7684\u6548\u7387\u3002\n\n\u94c1\u7d20\u4f53\u4e0d\u9508\u94a2\u4e0d\u4ec5\u4ef7\u683c\u76f8\u5bf9\u4f4e\u5ec9\u7a33\u5b9a\uff0c\u5176\u78c1\u5c5e\u6027\u8fd8\u652f\u6301\u4f7f\u7528\u65b0\u7684\u52a0\u70ed\u6280\u672f\uff0c\u56e0\u6b64\u5728\u98df\u54c1\u52a0\u5de5\u884c\u4e1a\u7684\u5e94\u7528\u8d8a\u6765\u8d8a\u5e7f\u6cdb\u3002\n\n\u4e00\u6839\u5706\u5f62\u7535\u78c1\u7ebf\u5708\u7f20\u7ed5\u5728\u4e00\u7ec4\u5177\u6709\u6d41\u4f53\u6d41\u52a8\u7684\u7ba1\u9053\u5468\u56f4\u3002\u901a\u8fc7\u7ebf\u5708\u7684\u4ea4\u6d41\u7535\u4f1a\u4ea7\u751f\u53ef\u7a7f\u900f\u7ba1\u9053\u7684\u4ea4\u53d8\u78c1\u573a\uff0c\u4ece\u800c\u5728\u7ba1\u9053\u5185\u4ea7\u751f\u6da1\u6d41\uff0c\u4f7f\u5176\u5347\u6e29\u3002\u7136\u540e\uff0c\u70ed\u91cf\u4f1a\u901a\u8fc7\u4f20\u5bfc\u65b9\u5f0f\u4f20\u9012\u5230\u6d41\u4f53\u3002\n\n\u672c\u4f8b\u4e3a\u8fd9\u7ec4\u7ba1\u9053\uff08\u6570\u91cf\u3001\u957f\u5ea6\u3001\u539a\u5ea6\u3001\u6750\u6599\uff09\u548c\u7ebf\u5708\uff08\u531d\u6570\u3001\u5bfc\u7ebf\u534a\u5f84\u3001\u7535\u6d41\u5bc6\u5ea6\u548c\u6fc0\u53d1\u9891\u7387\uff09\u63d0\u4f9b\u5404\u79cd\u6784\u578b\uff0c\u4ee5\u4f18\u5316\u4e0e\u6d41\u4f53\u7684\u70ed\u4ea4\u6362\uff0c\u540c\u65f6\u786e\u4fdd\u7ba1\u5185\u6e29\u5ea6\u5728\u7ed9\u5b9a\u6d41\u7387\u4e0b\u4fdd\u6301\u5747\u5300\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("inline_induction_heater.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
