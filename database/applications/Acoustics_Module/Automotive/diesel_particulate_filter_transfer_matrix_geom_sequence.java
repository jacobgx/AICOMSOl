/*
 * diesel_particulate_filter_transfer_matrix_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:25 by COMSOL 6.3.0.290. */
public class diesel_particulate_filter_transfer_matrix_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Automotive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("r_filter", "67[mm]");
    model.param().set("h_filter", "310[mm]");
    model.param().set("r_tube1", "27.5[mm]");
    model.param().set("h_tube1", "40[mm]");
    model.param().set("h_cone1", "28[mm]");
    model.param().set("r_tube2", "50[mm]");
    model.param().set("h_tube2", "75[mm]");
    model.param().set("h_cone2", "270[mm]");
    model.param().set("r_tube3", "74.5[mm]");
    model.param().set("h_tube3", "100[mm]");
    model.param().set("h_tube4", "29[mm]");
    model.param().set("h_tube5", "15[mm]");
    model.param().set("h_cone3", "28[mm]");
    model.param().set("r_tube6", "28.5[mm]");
    model.param().set("h_tube6", "57[mm]");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("h_block", "3[mm]");
    model.param("par2").set("hole_size", "1.5[mm]");
    model.param("par2").set("support_size", "1.5[mm]");
    model.param("par2").set("wall_size", "0.5[mm]");

    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").lengthUnit("mm");
    model.geom("part1").create("blk1", "Block");
    model.geom("part1").feature("blk1").set("size", new String[]{"h_filter", "hole_size", "1"});
    model.geom("part1").feature("blk1").setIndex("size", "hole_size", 2);
    model.geom("part1").feature("blk1").set("pos", new String[]{"h_tube4/2", "support_size/2", "0"});
    model.geom("part1").feature("blk1").setIndex("pos", "support_size/2", 2);
    model.geom("part1").feature("blk1").setIndex("layername", "Layer 1", 0);
    model.geom("part1").feature("blk1").setIndex("layer", "h_block", 0);
    model.geom("part1").feature("blk1").set("layerbottom", false);
    model.geom("part1").feature("blk1").set("layerleft", true);
    model.geom("part1").feature("blk1").set("layerright", true);
    model.geom("part1").run("blk1");
    model.geom("part1").create("arr1", "Array");
    model.geom("part1").feature("arr1").selection("input").set("blk1");
    model.geom("part1").feature("arr1").set("fullsize", new int[]{1, 2, 2});
    model.geom("part1").feature("arr1").set("displ", new String[]{"0", "hole_size+wall_size", "0"});
    model.geom("part1").feature("arr1").setIndex("displ", "hole_size+wall_size", 2);
    model.geom("part1").run("arr1");
    model.geom("part1").create("sel1", "ExplicitSelection");

    model.view("view2").set("renderwireframe", true);

    model.geom("part1").feature("sel1").selection("selection").set("arr1(1,1,1)", 2);
    model.geom("part1").feature("sel1").selection("selection").set("arr1(1,1,2)", 1, 2, 3);
    model.geom("part1").feature("sel1").selection("selection").set("arr1(1,2,1)", 1, 2, 3);
    model.geom("part1").feature("sel1").selection("selection").set("arr1(1,2,2)", 2);
    model.geom("part1").run("sel1");
    model.geom("part1").create("comsel1", "ComplementSelection");
    model.geom("part1").feature("comsel1").set("input", new String[]{"sel1"});
    model.geom("part1").run("comsel1");
    model.geom("part1").create("arr2", "Array");
    model.geom("part1").feature("arr2").selection("input").set("arr1");
    model.geom("part1").feature("arr2").set("fullsize", new int[]{1, 8, 8});
    model.geom("part1").feature("arr2").set("displ", new String[]{"0", "(hole_size+wall_size)*2", "0"});
    model.geom("part1").feature("arr2").setIndex("displ", "(hole_size+wall_size)*2", 2);
    model.geom("part1").run("arr2");
    model.geom("part1").create("arr3", "Array");
    model.geom("part1").feature("arr3").selection("input").set("arr2");
    model.geom("part1").feature("arr3").set("fullsize", new int[]{1, 2, 2});
    model.geom("part1").feature("arr3")
         .set("displ", new String[]{"0", "16*hole_size+15*wall_size+support_size", "0"});
    model.geom("part1").feature("arr3").setIndex("displ", "16*hole_size+15*wall_size+support_size", 2);
    model.geom("part1").run("arr3");
    model.geom("part1").create("cylsel1", "CylinderSelection");
    model.geom("part1").feature("cylsel1").set("entitydim", -1);
    model.geom("part1").feature("cylsel1").set("r", Double.POSITIVE_INFINITY);
    model.geom("part1").feature("cylsel1").set("rin", "r_filter");
    model.geom("part1").feature("cylsel1").set("axistype", "x");
    model.geom("part1").run("cylsel1");
    model.geom("part1").feature().create("del1", "Delete");
    model.geom("part1").feature("del1").selection("input").init();
    model.geom("part1").feature("del1").selection("input").named("cylsel1");
    model.geom("part1").run("del1");
    model.geom("part1").create("cyl1", "Cylinder");
    model.geom("part1").feature("cyl1").set("r", "r_filter");
    model.geom("part1").feature("cyl1").set("h", "h_tube4/2+h_filter+h_tube5/2");
    model.geom("part1").feature("cyl1").set("axistype", "x");
    model.geom("part1").feature("cyl1").setIndex("layername", "Layer 1", 0);
    model.geom("part1").feature("cyl1").setIndex("layer", "h_tube4/2", 0);
    model.geom("part1").feature("cyl1").setIndex("layername", "Layer 2", 1);
    model.geom("part1").feature("cyl1").setIndex("layer", "h_block", 1);
    model.geom("part1").feature("cyl1").setIndex("layername", "Layer 3", 2);
    model.geom("part1").feature("cyl1").setIndex("layer", "h_filter-2*h_block", 2);
    model.geom("part1").feature("cyl1").setIndex("layername", "Layer 4", 3);
    model.geom("part1").feature("cyl1").setIndex("layer", "h_block", 3);
    model.geom("part1").feature("cyl1").set("layerside", false);
    model.geom("part1").feature("cyl1").set("layerbottom", true);
    model.geom("part1").run("cyl1");
    model.geom().create("part2", "Part", 3);
    model.geom("part2").geomRep("comsol");
    model.geom("part2").lengthUnit("mm");
    model.geom("part2").create("cyl1", "Cylinder");
    model.geom("part2").feature("cyl1").set("r", "r_tube1");
    model.geom("part2").feature("cyl1").set("h", "h_tube1");
    model.geom("part2").feature("cyl1").set("axistype", "x");
    model.geom("part2").run("cyl1");
    model.geom("part2").create("cone1", "Cone");
    model.geom("part2").feature("cone1").set("specifytop", "radius");
    model.geom("part2").feature("cone1").set("r", "r_tube1");
    model.geom("part2").feature("cone1").set("h", "h_cone1");
    model.geom("part2").feature("cone1").set("rtop", "r_tube2");
    model.geom("part2").feature("cone1").set("pos", new String[]{"h_tube1", "0", "0"});
    model.geom("part2").feature("cone1").set("axistype", "x");
    model.geom("part2").run("cone1");
    model.geom("part2").create("cyl2", "Cylinder");
    model.geom("part2").feature("cyl2").set("r", "r_tube2");
    model.geom("part2").feature("cyl2").set("h", "h_tube2");
    model.geom("part2").feature("cyl2").set("pos", new String[]{"h_tube1+h_cone1", "0", "0"});
    model.geom("part2").feature("cyl2").set("axistype", "x");
    model.geom("part2").run("cyl2");
    model.geom("part2").create("cone2", "Cone");
    model.geom("part2").feature("cone2").set("specifytop", "radius");
    model.geom("part2").feature("cone2").set("r", "r_tube2");
    model.geom("part2").feature("cone2").set("h", "h_cone2");
    model.geom("part2").feature("cone2").set("rtop", "r_tube3");
    model.geom("part2").feature("cone2").set("pos", new String[]{"h_tube1+h_cone1+h_tube2", "0", "0"});
    model.geom("part2").feature("cone2").set("axistype", "x");
    model.geom("part2").run("cone2");
    model.geom("part2").create("cyl3", "Cylinder");
    model.geom("part2").feature("cyl3").set("r", "r_tube3");
    model.geom("part2").feature("cyl3").set("h", "h_tube3");
    model.geom("part2").feature("cyl3").set("pos", new String[]{"h_tube1+h_cone1+h_tube2+h_cone2", "0", "0"});
    model.geom("part2").feature("cyl3").set("axistype", "x");
    model.geom("part2").run("cyl3");
    model.geom("part2").create("cyl4", "Cylinder");
    model.geom("part2").feature("cyl4").set("r", "r_filter");
    model.geom("part2").feature("cyl4").set("h", "h_tube4/2");
    model.geom("part2").feature("cyl4")
         .set("pos", new String[]{"h_tube1+h_cone1+h_tube2+h_cone2+h_tube3", "0", "0"});
    model.geom("part2").feature("cyl4").set("axistype", "x");
    model.geom("part2").run("cyl4");
    model.geom().create("part3", "Part", 3);
    model.geom("part3").geomRep("comsol");
    model.geom("part3").lengthUnit("mm");
    model.geom("part3").create("cyl1", "Cylinder");
    model.geom("part3").feature("cyl1").set("r", "r_filter");
    model.geom("part3").feature("cyl1").set("h", "h_tube5/2");
    model.geom("part3").feature("cyl1").set("axistype", "x");
    model.geom("part3").run("cyl1");
    model.geom("part3").create("cone1", "Cone");
    model.geom("part3").feature("cone1").set("specifytop", "radius");
    model.geom("part3").feature("cone1").set("r", "r_filter");
    model.geom("part3").feature("cone1").set("h", "h_cone3");
    model.geom("part3").feature("cone1").set("rtop", "r_tube6");
    model.geom("part3").feature("cone1").set("pos", new String[]{"h_tube5/2", "0", "0"});
    model.geom("part3").feature("cone1").set("axistype", "x");
    model.geom("part3").run("cone1");
    model.geom("part3").create("cyl2", "Cylinder");
    model.geom("part3").feature("cyl2").set("r", "r_tube6");
    model.geom("part3").feature("cyl2").set("h", "h_tube6");
    model.geom("part3").feature("cyl2").set("pos", new String[]{"h_tube5/2+h_cone3", "0", "0"});
    model.geom("part3").feature("cyl2").set("axistype", "x");
    model.geom("part3").run("cyl2");
    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part2");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1")
         .set("displ", new String[]{"-h_tube1-h_cone1-h_tube2-h_cone2-h_tube3-h_tube4/2", "0", "0"});
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi3")
         .set("displ", new String[]{"h_tube4/2+h_filter+h_tube5/2", "0", "0"});

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("pi1", "pi2(810)", "pi3");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", 0);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "allvertices");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").named("boxsel1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "coordinates");
    model.component("comp1").geom("geom1").feature("wp2").setIndex("genpoints", 1, 2, 2);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("par2", "Partition");
    model.component("comp1").geom("geom1").feature("par2").selection("input")
         .set("del1", "pi2(1)", "pi2(10)", "pi2(100)", "pi2(101)", "pi2(102)", "pi2(103)", "pi2(104)", "pi2(105)", "pi2(106)", "pi2(107)", "pi2(108)", "pi2(109)", "pi2(11)", "pi2(110)", "pi2(111)", "pi2(112)", "pi2(113)", "pi2(114)", "pi2(115)", "pi2(116)", "pi2(117)", "pi2(118)", "pi2(119)", "pi2(12)", "pi2(120)", "pi2(121)", "pi2(122)", "pi2(123)", "pi2(124)", "pi2(125)", "pi2(126)", "pi2(127)", "pi2(128)", "pi2(129)", "pi2(13)", "pi2(130)", "pi2(131)", "pi2(132)", "pi2(133)", "pi2(134)", "pi2(135)", "pi2(136)", "pi2(137)", "pi2(138)", "pi2(139)", "pi2(14)", "pi2(140)", "pi2(141)", "pi2(142)", "pi2(143)", "pi2(144)", "pi2(145)", "pi2(146)", "pi2(147)", "pi2(148)", "pi2(149)", "pi2(15)", "pi2(150)", "pi2(151)", "pi2(152)", "pi2(153)", "pi2(154)", "pi2(155)", "pi2(156)", "pi2(157)", "pi2(158)", "pi2(159)", "pi2(16)", "pi2(160)", "pi2(161)", "pi2(162)", "pi2(163)", "pi2(164)", "pi2(165)", "pi2(166)", "pi2(167)", "pi2(168)", "pi2(169)", "pi2(17)", "pi2(170)", "pi2(171)", "pi2(172)", "pi2(173)", "pi2(174)", "pi2(175)", "pi2(176)", "pi2(177)", "pi2(178)", "pi2(179)", "pi2(18)", "pi2(180)", "pi2(181)", "pi2(182)", "pi2(183)", "pi2(184)", "pi2(185)", "pi2(186)", "pi2(187)", "pi2(188)", "pi2(189)", "pi2(19)", "pi2(190)", "pi2(191)", "pi2(192)", "pi2(193)", "pi2(194)", "pi2(195)", "pi2(196)", "pi2(197)", "pi2(198)", "pi2(199)", "pi2(2)", "pi2(20)", "pi2(200)", "pi2(201)", "pi2(202)", "pi2(203)", "pi2(204)", "pi2(205)", "pi2(206)", "pi2(207)", "pi2(208)", "pi2(209)", "pi2(21)", "pi2(210)", "pi2(211)", "pi2(212)", "pi2(213)", "pi2(214)", "pi2(215)", "pi2(216)", "pi2(217)", "pi2(218)", "pi2(219)", "pi2(22)", "pi2(220)", "pi2(221)", "pi2(222)", "pi2(223)", "pi2(224)", "pi2(225)", "pi2(226)", "pi2(227)", "pi2(228)", "pi2(229)", "pi2(23)", "pi2(230)", "pi2(231)", "pi2(232)", "pi2(233)", "pi2(234)", "pi2(235)", "pi2(236)", "pi2(237)", "pi2(238)", "pi2(239)", "pi2(24)", "pi2(240)", "pi2(241)", "pi2(242)", "pi2(243)", "pi2(244)", "pi2(245)", "pi2(246)", "pi2(247)", "pi2(248)", "pi2(249)", "pi2(25)", "pi2(250)", "pi2(251)", "pi2(252)", "pi2(253)", "pi2(254)", "pi2(255)", "pi2(256)", "pi2(257)", "pi2(258)", "pi2(259)", "pi2(26)", "pi2(260)", "pi2(261)", "pi2(262)", "pi2(263)", "pi2(264)", "pi2(265)", "pi2(266)", "pi2(267)", "pi2(268)", "pi2(269)", "pi2(27)", "pi2(270)", "pi2(271)", "pi2(272)", "pi2(273)", "pi2(274)", "pi2(275)", "pi2(276)", "pi2(277)", "pi2(278)", "pi2(279)", "pi2(28)", "pi2(280)", "pi2(281)", "pi2(282)", "pi2(283)", "pi2(284)", "pi2(285)", "pi2(286)", "pi2(287)", "pi2(288)", "pi2(289)", "pi2(29)", "pi2(290)", "pi2(291)", "pi2(292)", "pi2(293)", "pi2(294)", "pi2(295)", "pi2(296)", "pi2(297)", "pi2(298)", "pi2(299)", "pi2(3)", "pi2(30)", "pi2(300)", "pi2(301)", "pi2(302)", "pi2(303)", "pi2(304)", "pi2(305)", "pi2(306)", "pi2(307)", "pi2(308)", "pi2(309)", "pi2(31)", "pi2(310)", "pi2(311)", "pi2(312)", "pi2(313)", "pi2(314)", "pi2(315)", "pi2(316)", "pi2(317)", "pi2(318)", "pi2(319)", "pi2(32)", "pi2(320)", "pi2(321)", "pi2(322)", "pi2(323)", "pi2(324)", "pi2(325)", "pi2(326)", "pi2(327)", "pi2(328)", "pi2(329)", "pi2(33)", "pi2(330)", "pi2(331)", "pi2(332)", "pi2(333)", "pi2(334)", "pi2(335)", "pi2(336)", "pi2(337)", "pi2(338)", "pi2(339)", "pi2(34)", "pi2(340)", "pi2(341)", "pi2(342)", "pi2(343)", "pi2(344)", "pi2(345)", "pi2(346)", "pi2(347)", "pi2(348)", "pi2(349)", "pi2(35)", "pi2(350)", "pi2(351)", "pi2(352)", "pi2(353)", "pi2(354)", "pi2(355)", "pi2(356)", "pi2(357)", "pi2(358)", "pi2(359)", "pi2(36)", "pi2(360)", "pi2(361)", "pi2(362)", "pi2(363)", "pi2(364)", "pi2(365)", "pi2(366)", "pi2(367)", "pi2(368)", "pi2(369)", "pi2(37)", "pi2(370)", "pi2(371)", "pi2(372)", "pi2(373)", "pi2(374)", "pi2(375)", "pi2(376)", "pi2(377)", "pi2(378)", "pi2(379)", "pi2(38)", "pi2(380)", "pi2(381)", "pi2(382)", "pi2(383)", "pi2(384)", "pi2(385)", "pi2(386)", "pi2(387)", "pi2(388)", "pi2(389)", "pi2(39)", "pi2(390)", "pi2(391)", "pi2(392)", "pi2(393)", "pi2(394)", "pi2(395)", "pi2(396)", "pi2(397)", "pi2(398)", "pi2(399)", "pi2(4)", "pi2(40)", "pi2(400)", "pi2(401)", "pi2(402)", "pi2(403)", "pi2(404)", "pi2(405)", "pi2(406)", "pi2(407)", "pi2(408)", "pi2(409)", "pi2(41)", "pi2(410)", "pi2(411)", "pi2(412)", "pi2(413)", "pi2(414)", "pi2(415)", "pi2(416)", "pi2(417)", "pi2(418)", "pi2(419)", "pi2(42)", "pi2(420)", "pi2(421)", "pi2(422)", "pi2(423)", "pi2(424)", "pi2(425)", "pi2(426)", "pi2(427)", "pi2(428)", "pi2(429)", "pi2(43)", "pi2(430)", "pi2(431)", "pi2(432)", "pi2(433)", "pi2(434)", "pi2(435)", "pi2(436)", "pi2(437)", "pi2(438)", "pi2(439)", "pi2(44)", "pi2(440)", "pi2(441)", "pi2(442)", "pi2(443)", "pi2(444)", "pi2(445)", "pi2(446)", "pi2(447)", "pi2(448)", "pi2(449)", "pi2(45)", "pi2(450)", "pi2(451)", "pi2(452)", "pi2(453)", "pi2(454)", "pi2(455)", "pi2(456)", "pi2(457)", "pi2(458)", "pi2(459)", "pi2(46)", "pi2(460)", "pi2(461)", "pi2(462)", "pi2(463)", "pi2(464)", "pi2(465)", "pi2(466)", "pi2(467)", "pi2(468)", "pi2(469)", "pi2(47)", "pi2(470)", "pi2(471)", "pi2(472)", "pi2(473)", "pi2(474)", "pi2(475)", "pi2(476)", "pi2(477)", "pi2(478)", "pi2(479)", "pi2(48)", "pi2(480)", "pi2(481)", "pi2(482)", "pi2(483)", "pi2(484)", "pi2(485)", "pi2(486)", "pi2(487)", "pi2(488)", "pi2(489)", "pi2(49)", "pi2(490)", "pi2(491)", "pi2(492)", "pi2(493)", "pi2(494)", "pi2(495)", "pi2(496)", "pi2(497)", "pi2(498)", "pi2(499)", "pi2(5)", "pi2(50)", "pi2(500)", "pi2(501)", "pi2(502)", "pi2(503)", "pi2(504)", "pi2(505)", "pi2(506)", "pi2(507)", "pi2(508)", "pi2(509)", "pi2(51)", "pi2(510)", "pi2(511)", "pi2(512)", "pi2(513)", "pi2(514)", "pi2(515)", "pi2(516)", "pi2(517)", "pi2(518)", "pi2(519)", "pi2(52)", "pi2(520)", "pi2(521)", "pi2(522)", "pi2(523)", "pi2(524)", "pi2(525)", "pi2(526)", "pi2(527)", "pi2(528)", "pi2(529)", "pi2(53)", "pi2(530)", "pi2(531)", "pi2(532)", "pi2(533)", "pi2(534)", "pi2(535)", "pi2(536)", "pi2(537)", "pi2(538)", "pi2(539)", "pi2(54)", "pi2(540)", "pi2(541)", "pi2(542)", "pi2(543)", "pi2(544)", "pi2(545)", "pi2(546)", "pi2(547)", "pi2(548)", "pi2(549)", "pi2(55)", "pi2(550)", "pi2(551)", "pi2(552)", "pi2(553)", "pi2(554)", "pi2(555)", "pi2(556)", "pi2(557)", "pi2(558)", "pi2(559)", "pi2(56)", "pi2(560)", "pi2(561)", "pi2(562)", "pi2(563)", "pi2(564)", "pi2(565)", "pi2(566)", "pi2(567)", "pi2(568)", "pi2(569)", "pi2(57)", "pi2(570)", "pi2(571)", "pi2(572)", "pi2(573)", "pi2(574)", "pi2(575)", "pi2(576)", "pi2(577)", "pi2(578)", "pi2(579)", "pi2(58)", "pi2(580)", "pi2(581)", "pi2(582)", "pi2(583)", "pi2(584)", "pi2(585)", "pi2(586)", "pi2(587)", "pi2(588)", "pi2(589)", "pi2(59)", "pi2(590)", "pi2(591)", "pi2(592)", "pi2(593)", "pi2(594)", "pi2(595)", "pi2(596)", "pi2(597)", "pi2(598)", "pi2(599)", "pi2(6)", "pi2(60)", "pi2(600)", "pi2(601)", "pi2(602)", "pi2(603)", "pi2(604)", "pi2(605)", "pi2(606)", "pi2(607)", "pi2(608)", "pi2(609)", "pi2(61)", "pi2(610)", "pi2(611)", "pi2(612)", "pi2(613)", "pi2(614)", "pi2(615)", "pi2(616)", "pi2(617)", "pi2(618)", "pi2(619)", "pi2(62)", "pi2(620)", "pi2(621)", "pi2(622)", "pi2(623)", "pi2(624)", "pi2(625)", "pi2(626)", "pi2(627)", "pi2(628)", "pi2(629)", "pi2(63)", "pi2(630)", "pi2(631)", "pi2(632)", "pi2(633)", "pi2(634)", "pi2(635)", "pi2(636)", "pi2(637)", "pi2(638)", "pi2(639)", "pi2(64)", "pi2(640)", "pi2(641)", "pi2(642)", "pi2(643)", "pi2(644)", "pi2(645)", "pi2(646)", "pi2(647)", "pi2(648)", "pi2(649)", "pi2(65)", "pi2(650)", "pi2(651)", "pi2(652)", "pi2(653)", "pi2(654)", "pi2(655)", "pi2(656)", "pi2(657)", "pi2(658)", "pi2(659)", "pi2(66)", "pi2(660)", "pi2(661)", "pi2(662)", "pi2(663)", "pi2(664)", "pi2(665)", "pi2(666)", "pi2(667)", "pi2(668)", "pi2(669)", "pi2(67)", "pi2(670)", "pi2(671)", "pi2(672)", "pi2(673)", "pi2(674)", "pi2(675)", "pi2(676)", "pi2(677)", "pi2(678)", "pi2(679)", "pi2(68)", "pi2(680)", "pi2(681)", "pi2(682)", "pi2(683)", "pi2(684)", "pi2(685)", "pi2(686)", "pi2(687)", "pi2(688)", "pi2(689)", "pi2(69)", "pi2(690)", "pi2(691)", "pi2(692)", "pi2(693)", "pi2(694)", "pi2(695)", "pi2(696)", "pi2(697)", "pi2(698)", "pi2(699)", "pi2(7)", "pi2(70)", "pi2(700)", "pi2(701)", "pi2(702)", "pi2(703)", "pi2(704)", "pi2(705)", "pi2(706)", "pi2(707)", "pi2(708)", "pi2(709)", "pi2(71)", "pi2(710)", "pi2(711)", "pi2(712)", "pi2(713)", "pi2(714)", "pi2(715)", "pi2(716)", "pi2(717)", "pi2(718)", "pi2(719)", "pi2(72)", "pi2(720)", "pi2(721)", "pi2(722)", "pi2(723)", "pi2(724)", "pi2(725)", "pi2(726)", "pi2(727)", "pi2(728)", "pi2(729)", "pi2(73)", "pi2(730)", "pi2(731)", "pi2(732)", "pi2(733)", "pi2(734)", "pi2(735)", "pi2(736)", "pi2(737)", "pi2(738)", "pi2(739)", "pi2(74)", "pi2(740)", "pi2(741)", "pi2(742)", "pi2(743)", "pi2(744)", "pi2(745)", "pi2(746)", "pi2(747)", "pi2(748)", "pi2(749)", "pi2(75)", "pi2(750)", "pi2(751)", "pi2(752)", "pi2(753)", "pi2(754)", "pi2(755)", "pi2(756)", "pi2(757)", "pi2(758)", "pi2(759)", "pi2(76)", "pi2(760)", "pi2(761)", "pi2(762)", "pi2(763)", "pi2(764)", "pi2(765)", "pi2(766)", "pi2(767)", "pi2(768)", "pi2(769)", "pi2(77)", "pi2(770)", "pi2(771)", "pi2(772)", "pi2(773)", "pi2(774)", "pi2(775)", "pi2(776)", "pi2(777)", "pi2(778)", "pi2(779)", "pi2(78)", "pi2(780)", "pi2(781)", "pi2(782)", "pi2(783)", "pi2(784)", "pi2(785)", "pi2(786)", "pi2(787)", "pi2(788)", "pi2(789)", "pi2(79)", "pi2(790)", "pi2(791)", "pi2(792)", "pi2(793)", "pi2(794)", "pi2(795)", "pi2(796)", "pi2(797)", "pi2(798)", "pi2(799)", "pi2(8)", "pi2(80)", "pi2(800)", "pi2(801)", "pi2(802)", "pi2(803)", "pi2(804)", "pi2(805)", "pi2(806)", "pi2(807)", "pi2(808)", "pi2(809)", "pi2(81)", "pi2(82)", "pi2(83)", "pi2(84)", "pi2(85)", "pi2(86)", "pi2(87)", "pi2(88)", "pi2(89)", "pi2(9)", "pi2(90)", "pi2(91)", "pi2(92)", "pi2(93)", "pi2(94)", "pi2(95)", "pi2(96)", "pi2(97)", "pi2(98)", "pi2(99)");
    model.component("comp1").geom("geom1").feature("par2").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par2");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("par2");
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 45);
    model.component("comp1").geom("geom1").feature("rot1").set("selresult", true);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymax", 0);
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del2").selection("input").named("boxsel2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").named("rot1");
    model.component("comp1").geom("geom1").feature("rot2").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("rot2").set("rot", -45);
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("diesel_particulate_filter_transfer_matrix_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
